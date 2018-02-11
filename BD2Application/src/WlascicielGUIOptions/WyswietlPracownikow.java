package WlascicielGUIOptions;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import Application.LogIn;
import Application.Main;
import Application.WlascicielGUI;

import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class WyswietlPracownikow {

	private JFrame frame;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WyswietlPracownikow window = new WyswietlPracownikow();
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
					WyswietlPracownikow window = new WyswietlPracownikow();
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
	public WyswietlPracownikow() {
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
		} catch (ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (InstantiationException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IllegalAccessException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (UnsupportedLookAndFeelException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
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
				ZarzadzajPracownikami.NewScreen();
				frame.dispose();
			}
		});
		btnWstecz.setBounds(335, 52, 89, 23);
		frame.getContentPane().add(btnWstecz);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 93, 414, 157);
		frame.getContentPane().add(scrollPane);
		
		DefaultTableModel model = new DefaultTableModel();
		JTable table = new JTable(model);
		model.addColumn("Id"); 
		model.addColumn("Imiê");
		model.addColumn("Nazwisko");
		model.addColumn("Stanowisko");
		model.addColumn("Data zatrudnienia");
		model.addColumn("Wynagrodzenie");
		
		table.setAutoCreateRowSorter(true);//do sortowania
		TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
		table.setRowSorter(sorter);
		List<RowSorter.SortKey> sortKeys = new ArrayList<>();
		int columnIndexToSort = 0;
		sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING));
		sorter.setSortKeys(sortKeys);
		sorter.sort();
		
		
		ResultSet res = Main.db.SqlREAD("SELECT * FROM PRACOWNICY");
		try {
			while(res.next())
			{
				model.addRow(new Object[]{res.getInt(1), res.getString(2),res.getString(3),res.getString(4),
						res.getString(5),res.getString(6)
						});
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		scrollPane.setViewportView(table);
		
	}
}
