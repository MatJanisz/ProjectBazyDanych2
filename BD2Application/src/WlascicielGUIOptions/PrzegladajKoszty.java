package WlascicielGUIOptions;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import Application.LogIn;
import Application.Main;
import Application.WlascicielGUI;

public class PrzegladajKoszty {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrzegladajKoszty window = new PrzegladajKoszty();
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
					PrzegladajKoszty window = new PrzegladajKoszty();
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
	public PrzegladajKoszty() {
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
		
		JButton btnKosztyJednorazowe = new JButton("Koszty jednorazowe");
		btnKosztyJednorazowe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				WyswietlKosztyJednorazowe.NewScreen();
				frame.dispose();
			}
		});
		btnKosztyJednorazowe.setBounds(36, 100, 149, 23);
		frame.getContentPane().add(btnKosztyJednorazowe);
		
		JButton btnNewButton = new JButton("Koszty sta\u0142e");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WyswietlKosztyStale.NewScreen();
				frame.dispose();
			}
		});
		btnNewButton.setBounds(36, 134, 149, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnBilansFinansowy = new JButton("Bilans finansowy");
		btnBilansFinansowy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WyswietlBilansFinansowy.NewScreen();
				frame.dispose();
			}
		});
		btnBilansFinansowy.setBounds(203, 100, 140, 23);
		frame.getContentPane().add(btnBilansFinansowy);
		
	}
}
