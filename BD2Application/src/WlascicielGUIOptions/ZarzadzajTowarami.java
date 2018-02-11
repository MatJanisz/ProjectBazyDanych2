package WlascicielGUIOptions;

import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import Application.LogIn;
import Application.Main;
import Application.WlascicielGUI;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class ZarzadzajTowarami {

	private JFrame frame;
	private JTextField textFieldNazwa;
	private JTextField textFieldLiczba;
	private JTextField textFieldId;
	private JTextField textFieldCena;
	private JTextField textFieldKodKreskowy;
	private JTextField textFieldJednostka;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ZarzadzajTowarami window = new ZarzadzajTowarami();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void NewScreen() {
	EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				ZarzadzajTowarami window = new ZarzadzajTowarami();
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
	public ZarzadzajTowarami() {
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
		
		JLabel lblWlasciciel = new JLabel("Wlasciciel:");
		lblWlasciciel.setBounds(36, 24, 72, 14);
		frame.getContentPane().add(lblWlasciciel);
		
		JLabel lblNewLabel_1 = new JLabel(Main.imie+" "+Main.nazwisko);
		lblNewLabel_1.setBounds(102, 24, 186, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnWyloguj = new JButton("Wyloguj");
		btnWyloguj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new LogIn();
			}
		});
		btnWyloguj.setBounds(335, 20, 89, 23);
		frame.getContentPane().add(btnWyloguj);
		
		JLabel lblImi = new JLabel("Nazwa");
		lblImi.setBounds(36, 56, 39, 14);
		frame.getContentPane().add(lblImi);
		
		JLabel lblNazwisko = new JLabel("Liczba");
		lblNazwisko.setBounds(36, 87, 39, 14);
		frame.getContentPane().add(lblNazwisko);
		
		JLabel lblIdopcjonalne = new JLabel("Id(opcjonalne)");
		lblIdopcjonalne.setBounds(10, 236, 97, 17);
		frame.getContentPane().add(lblIdopcjonalne);
		
		textFieldNazwa = new JTextField();
		textFieldNazwa.setBounds(93, 43, 89, 28);
		frame.getContentPane().add(textFieldNazwa);
		textFieldNazwa.setColumns(10);
		
		textFieldLiczba = new JTextField();
		textFieldLiczba.setBounds(93, 80, 89, 28);
		frame.getContentPane().add(textFieldLiczba);
		textFieldLiczba.setColumns(10);
		
		textFieldId = new JTextField();
		textFieldId.setBounds(93, 230, 89, 28);
		frame.getContentPane().add(textFieldId);
		textFieldId.setColumns(10);
		
		JButton btnDodajKlienta = new JButton("Dodaj Towar");
		btnDodajKlienta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String nazwa = textFieldNazwa.getText();
					int liczba = Integer.valueOf(textFieldLiczba.getText());
					double cena = Double.valueOf(textFieldCena.getText());
					String kodKreskowy = textFieldKodKreskowy.getText();
					String jednostka = textFieldJednostka.getText();
					String data=DateTimeFormatter.ofPattern("yy/MM/dd").format(LocalDate.now());
					
					Main.db.SqlCUD("INSERT INTO TOWARY(NAZWA,LICZBA,CENA,DATA_DODANIA,KODKRESKOWY,JEDNOSTKASPRZEDAZY)"
							+ "VALUES('"+nazwa+"',"+liczba+","+cena+","+"'"+data+"'"+",'"+kodKreskowy+"','"+jednostka+"')");
					JOptionPane.showMessageDialog(frame, "Dodano towar","",JOptionPane.INFORMATION_MESSAGE);
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(frame, "Operacja nie powiod³a siê","",JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				} catch (HeadlessException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnDodajKlienta.setBounds(238, 102, 110, 23);
		frame.getContentPane().add(btnDodajKlienta);
		
		JButton btnUsuKlienta = new JButton("Usu\u0144 Towar");
		btnUsuKlienta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = textFieldId.getText();
				boolean isId = false;
				ResultSet res = Main.db.SqlREAD("SELECT ID FROM TOWARY");
				try {
					while(res.next())
					{
						if(id.equals(String.valueOf(res.getInt(1))))
						{
							isId = true;
							break;
						}
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				if(isId)
				{
					Main.db.SqlCUD("DELETE FROM TOWARY WHERE(id='"+id+"')");
					JOptionPane.showMessageDialog(frame, "Usuniêto towar","",JOptionPane.INFORMATION_MESSAGE);
				}
				else
					JOptionPane.showMessageDialog(frame, "Towar o takim Id nie istnieje","",JOptionPane.ERROR_MESSAGE);
				
				
			}
		});
		btnUsuKlienta.setBounds(238, 138, 104, 23);
		frame.getContentPane().add(btnUsuKlienta);
		
		JButton btnEdytujKlienta = new JButton("Edytuj Towar");
		btnEdytujKlienta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String id = textFieldId.getText();
					String nazwa = textFieldNazwa.getText();
					int liczba = Integer.valueOf(textFieldLiczba.getText());
					double cena = Double.valueOf(textFieldCena.getText());
					String kodKreskowy = textFieldKodKreskowy.getText();
					String jednostka = textFieldJednostka.getText();
					String data=DateTimeFormatter.ofPattern("yy/MM/dd").format(LocalDate.now());
					boolean isId = false;
					ResultSet res = Main.db.SqlREAD("SELECT ID FROM TOWARY");
					try {
						while(res.next())
						{
							if(id.equals(String.valueOf(res.getInt(1))))
							{
								isId = true;
								break;
							}
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
					if(isId)
					{
						Main.db.SqlCUD("UPDATE TOWARY"
								+ " SET NAZWA='"+nazwa+"',LICZBA="+liczba+",CENA="+cena+
								",DATA_DODANIA='"+data+"',KODKRESKOWY='"+kodKreskowy+"',JEDNOSTKASPRZEDAZY='"+jednostka+"'"
						+"WHERE ID='"+id+"'");
						JOptionPane.showMessageDialog(frame, "Zmieniono Towar","",JOptionPane.INFORMATION_MESSAGE);
					}
					else
						JOptionPane.showMessageDialog(frame, "Towar o takim Id nie istnieje","",JOptionPane.ERROR_MESSAGE);
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(frame, "Operacja nie powiod³a siê","",JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnEdytujKlienta.setBounds(238, 172, 116, 23);
		frame.getContentPane().add(btnEdytujKlienta);
		
		JButton btnWstecz = new JButton("Wstecz");
		btnWstecz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WlascicielGUI.NewScreen();
				frame.dispose();
			}
		});
		btnWstecz.setBounds(335, 52, 89, 23);
		frame.getContentPane().add(btnWstecz);
		
		JButton btnNewButton = new JButton("Wyswietl towary");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				WyswietlTowary.NewScreen();
				frame.dispose();
			}
		});
		btnNewButton.setBounds(238, 209, 138, 23);
		frame.getContentPane().add(btnNewButton);
		
		textFieldCena = new JTextField();
		textFieldCena.setBounds(93, 117, 89, 28);
		frame.getContentPane().add(textFieldCena);
		textFieldCena.setColumns(10);
		
		JLabel lblCena = new JLabel("Cena");
		lblCena.setBounds(37, 124, 38, 14);
		frame.getContentPane().add(lblCena);
		
		JLabel lblKodKreskowy = new JLabel("Kod kreskowy");
		lblKodKreskowy.setBounds(10, 160, 89, 17);
		frame.getContentPane().add(lblKodKreskowy);
		
		textFieldKodKreskowy = new JTextField();
		textFieldKodKreskowy.setBounds(93, 154, 89, 28);
		frame.getContentPane().add(textFieldKodKreskowy);
		textFieldKodKreskowy.setColumns(10);
		
		JLabel lblJednostka = new JLabel("Jednostka");
		lblJednostka.setBounds(11, 200, 64, 14);
		frame.getContentPane().add(lblJednostka);
		
		textFieldJednostka = new JTextField();
		textFieldJednostka.setBounds(93, 193, 89, 28);
		frame.getContentPane().add(textFieldJednostka);
		textFieldJednostka.setColumns(10);
		
	}
}
