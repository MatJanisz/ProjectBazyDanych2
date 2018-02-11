package WlascicielGUIOptions;

import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

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

public class ZarzadzajKlientami {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Zarz¹dzajKlientami window = new Zarz¹dzajKlientami();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/
	
	public static void NewScreen() {
	EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				ZarzadzajKlientami window = new ZarzadzajKlientami();
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
	public ZarzadzajKlientami() {
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
		
		JLabel lblImi = new JLabel("Imi\u0119:");
		lblImi.setBounds(36, 70, 72, 14);
		frame.getContentPane().add(lblImi);
		
		JLabel lblNazwisko = new JLabel("Nazwisko: ");
		lblNazwisko.setBounds(36, 110, 72, 14);
		frame.getContentPane().add(lblNazwisko);
		
		JLabel lblIdopcjonalne = new JLabel("Id(opcjonalne):");
		lblIdopcjonalne.setBounds(36, 147, 89, 14);
		frame.getContentPane().add(lblIdopcjonalne);
		
		textField = new JTextField();
		textField.setBounds(132, 63, 145, 29);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(132, 103, 145, 29);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(132, 140, 145, 29);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnDodajKlienta = new JButton("Dodaj Klienta");
		btnDodajKlienta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String imie = textField.getText();
				String nazwisko = textField_1.getText();
				Main.db.SqlCUD("INSERT INTO KLIENCI(IMIE,NAZWISKO)"
						+ "VALUES('"+imie+"','"+nazwisko+"')");
				if(Main.operationStatus)
					JOptionPane.showMessageDialog(frame, "Dodano klienta","",JOptionPane.INFORMATION_MESSAGE);
				Main.operationStatus = true;
			}
		});
		btnDodajKlienta.setBounds(38, 179, 110, 23);
		frame.getContentPane().add(btnDodajKlienta);
		
		JButton btnUsuKlienta = new JButton("Usu\u0144 klienta");
		btnUsuKlienta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String imie = textField.getText();
					String nazwisko = textField_1.getText();
					String id = textField_2.getText();
					boolean isId = false;
					
					ResultSet res = Main.db.SqlREAD("SELECT ID FROM KLIENCI");
					try {
						while(res.next())
						{
							if(Integer.valueOf(id) == res.getInt(1) || id=="")
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
						if(id.equals(""))
						{
							
								Main.db.SqlCUD("DELETE FROM KLIENCI WHERE(imie='"+imie+"' AND nazwisko='"+nazwisko+"')");
								JOptionPane.showMessageDialog(frame, "Usuniêto klienta","",JOptionPane.INFORMATION_MESSAGE);
							
									
						}
						else 
						{
							
							Main.db.SqlCUD("DELETE FROM KLIENCI WHERE(id='"+id+"')");
							JOptionPane.showMessageDialog(frame, "Usuniêto klienta","",JOptionPane.INFORMATION_MESSAGE);
						}
					}
					else 
						JOptionPane.showMessageDialog(frame, "Klient o takim Id nie istnieje","",JOptionPane.ERROR_MESSAGE);
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
		btnUsuKlienta.setBounds(168, 179, 104, 23);
		frame.getContentPane().add(btnUsuKlienta);
		
		JButton btnEdytujKlienta = new JButton("Edytuj klienta");
		btnEdytujKlienta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String imie = textField.getText();
					String nazwisko = textField_1.getText();
					String id = textField_2.getText();
					boolean isId = false;
					
					ResultSet res = Main.db.SqlREAD("SELECT ID FROM KLIENCI");
					try {
						while(res.next())
						{
							if(Integer.valueOf(id) == res.getInt(1) || id.equals(""))
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
						Main.db.SqlCUD("UPDATE KLIENCI"
								+ " SET IMIE='"+imie+"',NAZWISKO='"+nazwisko+"'"
						+"WHERE ID='"+id+"'");
						JOptionPane.showMessageDialog(frame, "Zmieniono Klienta","",JOptionPane.INFORMATION_MESSAGE);
					}
					else 
						JOptionPane.showMessageDialog(frame, "Klient o takim Id nie istnieje","",JOptionPane.ERROR_MESSAGE);
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
		btnEdytujKlienta.setBounds(294, 179, 116, 23);
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
		
		JButton btnNewButton = new JButton("Wyswietl klientow");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				WyswietlKlientow.NewScreen();
				frame.dispose();
			}
		});
		btnNewButton.setBounds(36, 213, 150, 23);
		frame.getContentPane().add(btnNewButton);
		
	}
}
