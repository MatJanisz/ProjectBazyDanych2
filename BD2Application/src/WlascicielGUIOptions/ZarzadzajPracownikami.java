package WlascicielGUIOptions;

import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class ZarzadzajPracownikami {

	private JFrame frame;
	private JTextField textFieldImie;
	private JTextField textFieldNazwisko;
	private JTextField textFieldWynagrodzenie;
	private JTextField textFieldID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ZarzadzajPracownikami window = new ZarzadzajPracownikami();
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
					ZarzadzajPracownikami window = new ZarzadzajPracownikami();
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
	public ZarzadzajPracownikami() {
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
				frame.dispose();
			}
		});
		btnWyloguj.setBounds(335, 20, 89, 23);
		frame.getContentPane().add(btnWyloguj);
		
		JButton btnWstecz = new JButton("Wstecz");
		btnWstecz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WlascicielGUI.NewScreen();
				frame.dispose();
			}
		});
		btnWstecz.setBounds(335, 52, 89, 23);
		frame.getContentPane().add(btnWstecz);
		
		JLabel lblImi = new JLabel("Imi\u0119");
		lblImi.setBounds(70, 56, 46, 14);
		frame.getContentPane().add(lblImi);
		
		textFieldImie = new JTextField();
		textFieldImie.setBounds(122, 49, 86, 26);
		frame.getContentPane().add(textFieldImie);
		textFieldImie.setColumns(10);
		
		JLabel lblNazwisko = new JLabel("Nazwisko");
		lblNazwisko.setBounds(51, 92, 59, 14);
		frame.getContentPane().add(lblNazwisko);
		
		textFieldNazwisko = new JTextField();
		textFieldNazwisko.setBounds(122, 86, 86, 26);
		frame.getContentPane().add(textFieldNazwisko);
		textFieldNazwisko.setColumns(10);
		
		JLabel lblWynagrodzenie = new JLabel("Wynagrodzenie");
		lblWynagrodzenie.setBounds(20, 129, 96, 14);
		frame.getContentPane().add(lblWynagrodzenie);
		
		textFieldWynagrodzenie = new JTextField();
		textFieldWynagrodzenie.setBounds(122, 123, 86, 26);
		frame.getContentPane().add(textFieldWynagrodzenie);
		textFieldWynagrodzenie.setColumns(10);
		
		JComboBox comboBox = new JComboBox(new String[] {"wlasciciel","ksiegowy","sprzedawca","magazynier"});
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"wlasciciel", "ksiegowy", "sprzedawca", "magazynier"}));
		comboBox.setBounds(122, 162, 103, 20);
		frame.getContentPane().add(comboBox);
		
		JLabel lblStanowisko = new JLabel("Stanowisko");
		lblStanowisko.setBounds(36, 165, 72, 14);
		frame.getContentPane().add(lblStanowisko);
		
		textFieldID = new JTextField();
		textFieldID.setBounds(122, 193, 86, 26);
		frame.getContentPane().add(textFieldID);
		textFieldID.setColumns(10);
		
		JLabel lblIdopcjonalnie = new JLabel("Id(opcjonalnie)");
		lblIdopcjonalnie.setBounds(24, 199, 92, 14);
		frame.getContentPane().add(lblIdopcjonalnie);
		
		JButton btnDodajPracownika = new JButton("Dodaj pracownika");
		btnDodajPracownika.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String imie = textFieldImie.getText();
					String nazwisko = textFieldNazwisko.getText();
					String stanowisko = comboBox.getSelectedItem().toString();
					String data=DateTimeFormatter.ofPattern("yy/MM/dd").format(LocalDate.now());
					int wynagrodzenie = Integer.valueOf(textFieldWynagrodzenie.getText());
					Main.db.SqlCUD("INSERT INTO PRACOWNICY(IMIE,NAZWISKO,STANOWISKO,DATA_ZATRUDNIENIA,WYNAGRODZENIE)"
							+ "VALUES('"+imie+"','"+nazwisko+"','"+stanowisko+"','"+data+"',"+wynagrodzenie+")");
					if(Main.operationStatus)
						JOptionPane.showMessageDialog(frame, "Dodano pracownika","",JOptionPane.INFORMATION_MESSAGE);
					
					Main.operationStatus = true;
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(frame, "Operacja nie powiod³a siê","",JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				} catch (HeadlessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnDodajPracownika.setBounds(258, 95, 166, 23);
		frame.getContentPane().add(btnDodajPracownika);
		
		JButton btnUsunPracownika = new JButton("Usun pracownika");
		btnUsunPracownika.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String id = textFieldID.getText();
				boolean isId = false;
				ResultSet res = Main.db.SqlREAD("SELECT ID FROM PRACOWNICY");
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
					Main.db.SqlCUD("DELETE FROM PRACOWNICY WHERE(id='"+id+"')");
					JOptionPane.showMessageDialog(frame, "Usuniêto Pracownika","",JOptionPane.INFORMATION_MESSAGE);
				}
				else
					JOptionPane.showMessageDialog(frame, "Pracownik o takim Id nie istnieje","",JOptionPane.ERROR_MESSAGE);
				}
				
		});
		btnUsunPracownika.setBounds(258, 129, 166, 23);
		frame.getContentPane().add(btnUsunPracownika);
		
		JButton btnEdytujPracownika = new JButton("Edytuj pracownika");
		btnEdytujPracownika.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String id = textFieldID.getText();
					String imie = textFieldImie.getText();
					String nazwisko = textFieldNazwisko.getText();
					String stanowisko = comboBox.getSelectedItem().toString();
					String data=DateTimeFormatter.ofPattern("yy/MM/dd").format(LocalDate.now());
					int wynagrodzenie = Integer.valueOf(textFieldWynagrodzenie.getText());
					boolean isId = false;
					ResultSet res = Main.db.SqlREAD("SELECT ID FROM PRACOWNICY");
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
					if(isId) {
						Main.db.SqlCUD("UPDATE PRACOWNICY"
								+ " SET IMIE='"+imie+"',NAZWISKO='"+nazwisko+"',STANOWISKO='"+stanowisko+
								"',DATA_ZATRUDNIENIA='"+data+"',WYNAGRODZENIE="+wynagrodzenie
						+"WHERE ID='"+id+"'");
						JOptionPane.showMessageDialog(frame, "Zmieniono Pracownika","",JOptionPane.INFORMATION_MESSAGE);
					}
					else
						JOptionPane.showMessageDialog(frame, "Pracownik o takim Id nie istnieje","",JOptionPane.ERROR_MESSAGE);
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(frame, "Operacja nie powiod³a siê","",JOptionPane.ERROR_MESSAGE);

					e1.printStackTrace();
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnEdytujPracownika.setBounds(258, 161, 166, 23);
		frame.getContentPane().add(btnEdytujPracownika);
		
		JButton btnWywietlPracownikw = new JButton("Wy\u015Bwietl pracownik\u00F3w");
		btnWywietlPracownikw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new WyswietlPracownikow().NewScreen();
				frame.dispose();
			}
		});
		btnWywietlPracownikw.setBounds(258, 192, 166, 23);
		frame.getContentPane().add(btnWywietlPracownikw);
		
		
		
		
	}
}
