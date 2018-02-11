package Application;

import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableColumnModel;

import WlascicielGUIOptions.PrzegladajKoszty;
import WlascicielGUIOptions.WyswietlBilansFinansowy;
import WlascicielGUIOptions.ZarzadzajKlientami;
import WlascicielGUIOptions.ZarzadzajPracownikami;
import WlascicielGUIOptions.ZarzadzajTowarami;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class MagazynierGUI {

	private JFrame frame;
	private JTextField textFieldID;
	private JTextField textFieldXElement;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MagazynierGUI window = new MagazynierGUI();
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
					MagazynierGUI window = new MagazynierGUI();
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
	public MagazynierGUI() {
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
		
		JLabel lblWlasciciel = new JLabel("Magazynier:");
		lblWlasciciel.setBounds(36, 24, 72, 14);
		
		JLabel lblNewLabel_1 = new JLabel(Main.imie+" "+Main.nazwisko);
		lblNewLabel_1.setBounds(139, 24, 186, 14);
		
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
		
		textFieldID = new JTextField();
		textFieldID.setBounds(142, 81, 86, 29);
		frame.getContentPane().add(textFieldID);
		textFieldID.setColumns(10);
		
		textFieldXElement = new JTextField();
		textFieldXElement.setBounds(142, 121, 86, 29);
		frame.getContentPane().add(textFieldXElement);
		textFieldXElement.setColumns(10);
		
		JLabel lblId = new JLabel("Id");
		lblId.setBounds(99, 84, 33, 14);
		frame.getContentPane().add(lblId);
		
		JLabel lblDodajElementy = new JLabel("Dodaj x element\u00F3w");
		lblDodajElementy.setBounds(22, 128, 110, 14);
		frame.getContentPane().add(lblDodajElementy);
		
		JButton btnDodajTowary = new JButton("Dodaj towary");
		btnDodajTowary.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String id = textFieldID.getText();
					int xEl = Integer.valueOf(textFieldXElement.getText());
					int actualNumber=0;
					
					ResultSet res = Main.db.SqlREAD("SELECT LICZBA FROM TOWARY WHERE ID='"+id+"'");
					try {
						while(res.next())
						{
							actualNumber=res.getInt(1);
						}
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
					
						Main.db.SqlCUD("UPDATE TOWARY"
								+ " SET LICZBA="+actualNumber+xEl
						+"WHERE ID='"+id+"'");
						JOptionPane.showMessageDialog(frame, "Dodano towar","",JOptionPane.INFORMATION_MESSAGE);
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(frame, "Operacja nie powiod³a siê","",JOptionPane.ERROR_MESSAGE);
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			
				}
		});
		btnDodajTowary.setBounds(99, 162, 129, 23);
		frame.getContentPane().add(btnDodajTowary);
		
		JButton btnWywietlTowary = new JButton("Wy\u015Bwietl towary");
		btnWywietlTowary.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MagazynierGUIOptions.WyswietlTowary.NewScreen();
				frame.dispose();
			}
		});
		btnWywietlTowary.setBounds(99, 196, 129, 23);
		frame.getContentPane().add(btnWywietlTowary);
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
