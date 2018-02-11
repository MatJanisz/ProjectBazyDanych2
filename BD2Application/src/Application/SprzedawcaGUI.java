package Application;

import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.TableColumnModel;

import WlascicielGUIOptions.PrzegladajKoszty;
import WlascicielGUIOptions.WyswietlBilansFinansowy;
import WlascicielGUIOptions.ZarzadzajKlientami;
import WlascicielGUIOptions.ZarzadzajPracownikami;
import WlascicielGUIOptions.ZarzadzajTowarami;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class SprzedawcaGUI {

	private JFrame frame;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SprzedawcaGUI window = new SprzedawcaGUI();
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
					SprzedawcaGUI window = new SprzedawcaGUI();
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
	public SprzedawcaGUI() {
		ResultSet res = Main.db.SqlREAD("SELECT IMIE, NAZWISKO FROM PRACOWNICY WHERE ID="+String.valueOf(Main.userId));
		try {
			while(res.next())
			{
				Main.imie = res.getString(1);
				Main.nazwisko = res.getString(2);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
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
		lblWlasciciel.setBounds(36, 24, 80, 14);
		
		JLabel lblNewLabel_1 = new JLabel(Main.imie+" "+Main.nazwisko);
		lblNewLabel_1.setBounds(119, 24, 186, 14);
		
		JButton btnWyloguj = new JButton("Wyloguj");
		btnWyloguj.setBounds(335, 20, 89, 23);
		btnWyloguj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new LogIn();
				frame.dispose();
			}
		});
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(lblNewLabel_1);
		frame.getContentPane().add(lblWlasciciel);
		frame.getContentPane().add(btnWyloguj);
		
		JButton btnDodajKlienta = new JButton("Dodaj klienta");
		btnDodajKlienta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SprzedawcaGUIOptions.DodajKlienta.NewScreen();
				frame.dispose();
			}
		});
		btnDodajKlienta.setBounds(121, 93, 151, 23);
		frame.getContentPane().add(btnDodajKlienta);
		
		JButton btnWprowadzSprzeda = new JButton("Wprowadz sprzeda\u017C");
		btnWprowadzSprzeda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SprzedawcaGUIOptions.WprowadzSprzedaz.NewScreen();
				frame.dispose();
			}
		});
		btnWprowadzSprzeda.setBounds(121, 127, 151, 23);
		frame.getContentPane().add(btnWprowadzSprzeda);
	}
	
	public static void setColumnWidths(JTable table, int... widths) {
	    TableColumnModel columnModel = table.getColumnModel();
	    for (int i = 0; i < widths.length; i++) {
	        if (i < columnModel.getColumnCount()) {
	            columnModel.getColumn(i).setMaxWidth(widths[i]);
	        }
	        else break;
	    }
	}
}
