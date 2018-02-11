package SprzedawcaGUIOptions;

import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import Application.LogIn;
import Application.Main;
import Application.SprzedawcaGUI;
import Application.WlascicielGUI;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class DodajKlienta {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;

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
				DodajKlienta window = new DodajKlienta();
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
	public DodajKlienta() {
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
		
		JLabel lblImi = new JLabel("Imi\u0119:");
		lblImi.setBounds(36, 81, 72, 14);
		frame.getContentPane().add(lblImi);
		
		JLabel lblNazwisko = new JLabel("Nazwisko: ");
		lblNazwisko.setBounds(36, 116, 72, 14);
		frame.getContentPane().add(lblNazwisko);
		
		textField = new JTextField();
		textField.setBounds(127, 78, 145, 27);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(127, 113, 145, 27);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnDodajKlienta = new JButton("Dodaj Klienta");
		btnDodajKlienta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					try {
						String imie = textField.getText();
						String nazwisko = textField_1.getText();
						Main.db.SqlCUD("INSERT INTO KLIENCI(IMIE,NAZWISKO)"
								+ "VALUES('"+imie+"','"+nazwisko+"')");
						if(Main.operationStatus)
							JOptionPane.showMessageDialog(frame, "Dodano klienta","",JOptionPane.INFORMATION_MESSAGE);
						Main.operationStatus=true;
					} catch (HeadlessException e1) {
						JOptionPane.showMessageDialog(frame, "Operacja nie powiod³a siê","",JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}
				
			}
		});
		btnDodajKlienta.setBounds(36, 160, 110, 23);
		frame.getContentPane().add(btnDodajKlienta);
		
		JButton btnWstecz = new JButton("Wstecz");
		btnWstecz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SprzedawcaGUI.NewScreen();
				frame.dispose();
			}
		});
		btnWstecz.setBounds(335, 52, 89, 23);
		frame.getContentPane().add(btnWstecz);
		
		JButton btnWywietlKlientw = new JButton("Wy\u015Bwietl klient\u00F3w");
		btnWywietlKlientw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SprzedawcaGUIOptions.WyswietlKlientow.NewScreen();
				frame.dispose();
			}
		});
		btnWywietlKlientw.setBounds(156, 160, 139, 23);
		frame.getContentPane().add(btnWywietlKlientw);
		
	}
}
