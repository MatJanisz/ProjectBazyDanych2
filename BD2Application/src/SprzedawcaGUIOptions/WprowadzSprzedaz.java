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

public class WprowadzSprzedaz {
	private JFrame frame;

	 
	private static String[] fillComboBox(){
		ResultSet res = Main.db.SqlREAD("SELECT * FROM KLIENCI");
		ArrayList <String> strList = new ArrayList<>();
		try {
			while(res.next()){
				StringBuilder sb = new StringBuilder();
				sb.append(String.valueOf(res.getInt(1)));
				sb.append(' ');
				sb.append(res.getString(2));
				sb.append(' ');
				sb.append(res.getString(3));
				strList.add(sb.toString());
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String [] strArray = new String[strList.size()];
		strArray = strList.toArray(strArray);
		return strArray;
	}
	
	public static void NewScreen() {
	EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				WprowadzSprzedaz window = new WprowadzSprzedaz();
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
	public WprowadzSprzedaz() {
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
		lblWlasciciel.setBounds(36, 24, 81, 14);
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
		
		JLabel lblIDKlienta = new JLabel("ID Klienta:");
		lblIDKlienta.setBounds(36, 117, 72, 14);
		frame.getContentPane().add(lblIDKlienta);
		
		

		
		JButton btnWstecz = new JButton("Wstecz");
		btnWstecz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SprzedawcaGUI.NewScreen();
				frame.dispose();
			}
		});
		btnWstecz.setBounds(335, 52, 89, 23);
		frame.getContentPane().add(btnWstecz);
		
		JComboBox<String> comboBox = new JComboBox<String>(fillComboBox());
		comboBox.setBounds(127, 112, 198, 25);
		frame.getContentPane().add(comboBox);
		
		
		
		JButton btnRozpocznijSprzedaz = new JButton("Rozpocznij Sprzeda\u017C");
		btnRozpocznijSprzedaz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int IDKlienta  = Integer.parseInt(comboBox.getSelectedItem().toString().split(" ")[0]);
				Main.db.SqlCUD("INSERT INTO SPRZEDAZE(PRACOWNICYID,KLIENCIID)"
						+ "VALUES('"+Main.userId+"',"+IDKlienta+")");
				JOptionPane.showMessageDialog(frame, "Dodano zlecenie sprzeda¿y","",JOptionPane.INFORMATION_MESSAGE);
				ResultSet rs  = Main.db.SqlREAD("SELECT SEQ_SPRZEDAZE.CURRVAL FROM DUAL");
				try {
					rs.next();
					TowarySprzedaze.NewScreen(rs.getInt(1));
					frame.dispose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnRozpocznijSprzedaz.setBounds(139, 183, 145, 23);
		frame.getContentPane().add(btnRozpocznijSprzedaz);
		
	}
}
