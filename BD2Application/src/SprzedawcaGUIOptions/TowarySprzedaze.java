package SprzedawcaGUIOptions;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ListSelectionEvent;

import Application.LogIn;
import Application.Main;
import Application.SprzedawcaGUI;
import javax.swing.JComboBox;

public class TowarySprzedaze {
	private JFrame frame;
	private JTextField textFieldLiczbaTowaru;
	private int idSprzedazy;
	private static ArrayList<Integer> listaIlosciTowaru = new ArrayList<>();
	private static ArrayList<Integer> cenaTowaru = new ArrayList<>();
	private static JLabel lblDostepnaLiczbaVariable;
	private static JComboBox<String> comboBox;

	private static void selectedItemChanged(){
		if(comboBox.getSelectedIndex()!=-1){
		lblDostepnaLiczbaVariable.setText(listaIlosciTowaru.get(comboBox.getSelectedIndex()).toString());
		}
	}
	 
	private static String[] fillComboBox(){
		ResultSet res = Main.db.SqlREAD("SELECT ID,NAZWA,JEDNOSTKASPRZEDAZY,LICZBA,CENA FROM TOWARY");
		listaIlosciTowaru.clear();
		cenaTowaru.clear();
		ArrayList <String> strList = new ArrayList<>();
		try {
			while(res.next()){
				StringBuilder sb = new StringBuilder();
				sb.append(String.valueOf(res.getInt(1)));
				sb.append(' ');
				sb.append(res.getString(2));
				sb.append(" // ");
				sb.append(res.getString(3));
				strList.add(sb.toString());
				listaIlosciTowaru.add(res.getInt(4));
				cenaTowaru.add(res.getInt(5));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String [] strArray = new String[strList.size()];
		strArray = strList.toArray(strArray);
		lblDostepnaLiczbaVariable.setText(listaIlosciTowaru.get(0).toString());
		return strArray;
	}
	
	public static void NewScreen(int idSprzedazy) {
	EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				TowarySprzedaze window = new TowarySprzedaze(idSprzedazy);
				window.frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	});
}


	/**
	 * Create the application.
	 */
	public TowarySprzedaze(int idSprzedazy) {
		this.idSprzedazy=idSprzedazy;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		SwingUtilities.updateComponentTreeUI(frame);
		
		JLabel lblWlasciciel = new JLabel("Sprzedawca:");
		lblWlasciciel.setBounds(29, 24, 81, 14);
		frame.getContentPane().add(lblWlasciciel);
		
		JLabel lblNewLabel_1 = new JLabel(Main.imie+" "+Main.nazwisko);
		lblNewLabel_1.setBounds(127, 24, 186, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnWyloguj = new JButton("Wyloguj");
		btnWyloguj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new LogIn();
				frame.dispose();
			}
		});
		btnWyloguj.setBounds(335, 20, 89, 23);
		frame.getContentPane().add(btnWyloguj);
		
		JLabel lblTowar = new JLabel("Towar:");
		lblTowar.setBounds(29, 61, 72, 14);
		frame.getContentPane().add(lblTowar);
		
		

		
		JButton btnWstecz = new JButton("Wstecz");
		btnWstecz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SprzedawcaGUI.NewScreen();
				frame.dispose();
			}
		});
		btnWstecz.setBounds(335, 52, 89, 23);
		frame.getContentPane().add(btnWstecz);
		
		
		
		
		JButton btnRozpocznijSprzedaz = new JButton("Dokonaj sprzeda\u017Cy");
		btnRozpocznijSprzedaz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int orderedAmount = Integer.parseInt(textFieldLiczbaTowaru.getText());
				if(orderedAmount<=listaIlosciTowaru.get(comboBox.getSelectedIndex())){
				int idTowaru  = Integer.parseInt(comboBox.getSelectedItem().toString().split(" ")[0]);
				Main.db.SqlCUD("INSERT INTO SPRZEDAZE_TOWARY(\"Sprzedaze ID\",\"Towary ID\",ILOSC,CALKOWITY_KOSZ)"
						+ "VALUES("+idSprzedazy+","+idTowaru+","+orderedAmount+","+orderedAmount*cenaTowaru.get(comboBox.getSelectedIndex())+")");
				
				comboBox.removeAllItems();
				String[] arr =fillComboBox();
				for(String x: arr){
					comboBox.addItem(x);
					}
				if(Main.operationStatus){
					JOptionPane.showMessageDialog(frame, "Dodano  sprzeda¿y","",JOptionPane.INFORMATION_MESSAGE);
				}
				}		
				else{
					JOptionPane.showMessageDialog(frame, "Zamówienie przekracza dostêpn¹ iloœæ towaru","",JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnRozpocznijSprzedaz.setBounds(139, 183, 145, 23);
		frame.getContentPane().add(btnRozpocznijSprzedaz);
		
		JLabel labelIlosc = new JLabel("Liczba:");
		labelIlosc.setBounds(29, 129, 72, 14);
		frame.getContentPane().add(labelIlosc);
		
		textFieldLiczbaTowaru = new JTextField();
		textFieldLiczbaTowaru.setBounds(127, 126, 186, 25);
		frame.getContentPane().add(textFieldLiczbaTowaru);
		textFieldLiczbaTowaru.setColumns(10);
		
		JLabel lblDostepnaLiczba = new JLabel("Dost\u0119pna Liczba:");
		lblDostepnaLiczba.setBounds(29, 99, 88, 14);
		frame.getContentPane().add(lblDostepnaLiczba);
		
		 lblDostepnaLiczbaVariable = new JLabel("");
		lblDostepnaLiczbaVariable.setBounds(127, 101, 186, 14);
		frame.getContentPane().add(lblDostepnaLiczbaVariable);
		
		comboBox = new JComboBox<String>(fillComboBox());
		comboBox.setBounds(127, 56, 186, 25);
		frame.getContentPane().add(comboBox);
		comboBox.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		        selectedItemChanged();
		    }
		});
		
		
	}
}
