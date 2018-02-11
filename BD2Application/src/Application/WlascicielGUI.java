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
import WlascicielGUIOptions.ZarzadzajKlientami;
import WlascicielGUIOptions.ZarzadzajPracownikami;
import WlascicielGUIOptions.ZarzadzajTowarami;

public class WlascicielGUI {

	private JFrame frame;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WlascicielGUI window = new WlascicielGUI();
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
					WlascicielGUI window = new WlascicielGUI();
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
	public WlascicielGUI() {
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
		
		JButton btnZarzdzajKlientami = new JButton("Zarz\u0105dzaj klientami");
		btnZarzdzajKlientami.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ZarzadzajKlientami.NewScreen();
				frame.dispose();
			}
		});
		btnZarzdzajKlientami.setBounds(36, 80, 162, 23);
		frame.getContentPane().add(btnZarzdzajKlientami);
		
		JButton btnNewButton = new JButton("Przegl\u0105daj koszty");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PrzegladajKoszty.NewScreen();
				frame.dispose();
			}
		});
		btnNewButton.setBounds(36, 114, 162, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Zarz\u0105dzaj towarami");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ZarzadzajTowarami.NewScreen();
				frame.dispose();
			}
		});
		btnNewButton_1.setBounds(227, 80, 179, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnZarzdzajPracownikami = new JButton("Zarz\u0105dzaj pracownikami");
		btnZarzdzajPracownikami.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ZarzadzajPracownikami.NewScreen();
				frame.dispose();
			}
		});
		btnZarzdzajPracownikami.setBounds(227, 114, 179, 23);
		frame.getContentPane().add(btnZarzdzajPracownikami);
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
