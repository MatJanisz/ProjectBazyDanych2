package KsiegowyGUIOptions;

import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import Application.KsiegowyGUI;
import Application.LogIn;
import Application.Main;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class ZarzadzajKosztyJednorazowe {

	private JFrame frame;
	private JTextField textFieldNazwa;
	private JTextField textFieldLiczba;
	private JTextField textFieldNazwaTowaru;
	private JTextField textFieldCena;
	private JTextField textFieldId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ZarzadzajKosztyJednorazowe window = new ZarzadzajKosztyJednorazowe();
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
					ZarzadzajKosztyJednorazowe window = new ZarzadzajKosztyJednorazowe();
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
	public ZarzadzajKosztyJednorazowe() {
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
		
		JLabel lblWlasciciel = new JLabel("Ksiêgowy:");
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
				KsiegowyGUI.NewScreen();
				frame.dispose();
			}
		});
		btnWstecz.setBounds(335, 52, 89, 23);
		frame.getContentPane().add(btnWstecz);
		
		textFieldNazwa = new JTextField();
		textFieldNazwa.setBounds(125, 63, 89, 28);
		frame.getContentPane().add(textFieldNazwa);
		textFieldNazwa.setColumns(10);
		
		textFieldLiczba = new JTextField();
		textFieldLiczba.setBounds(125, 102, 89, 28);
		frame.getContentPane().add(textFieldLiczba);
		textFieldLiczba.setColumns(10);
		
		textFieldNazwaTowaru = new JTextField();
		textFieldNazwaTowaru.setBounds(125, 142, 89, 28);
		frame.getContentPane().add(textFieldNazwaTowaru);
		textFieldNazwaTowaru.setColumns(10);
		
		textFieldCena = new JTextField();
		textFieldCena.setBounds(125, 181, 89, 28);
		frame.getContentPane().add(textFieldCena);
		textFieldCena.setColumns(10);
		
		textFieldId = new JTextField();
		textFieldId.setBounds(125, 220, 89, 28);
		frame.getContentPane().add(textFieldId);
		textFieldId.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nazwa");
		lblNewLabel.setBounds(52, 66, 46, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblLiczba = new JLabel("Liczba");
		lblLiczba.setBounds(52, 109, 46, 14);
		frame.getContentPane().add(lblLiczba);
		
		JLabel lblNazwa = new JLabel("Nazwa towaru");
		lblNazwa.setBounds(26, 149, 82, 14);
		frame.getContentPane().add(lblNazwa);
		
		JLabel lblCena = new JLabel("Cena");
		lblCena.setBounds(52, 188, 46, 14);
		frame.getContentPane().add(lblCena);
		
		JLabel lblIdopcjonalnie = new JLabel("Id(opcjonalnie)");
		lblIdopcjonalnie.setBounds(26, 224, 99, 14);
		frame.getContentPane().add(lblIdopcjonalnie);
		
		JButton btnDodajKoszt = new JButton("Dodaj koszt");
		btnDodajKoszt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String nazwa = textFieldNazwa.getText();
					int liczba = Integer.valueOf(textFieldLiczba.getText());
					String nazwaTowaru = textFieldNazwaTowaru.getText();
					int cena = Integer.valueOf(textFieldCena.getText());
					String data=DateTimeFormatter.ofPattern("yy/MM/dd").format(LocalDate.now());
					Main.db.SqlCUD("INSERT INTO KOSZTY_JEDNORAZOWE(NAZWA,LICZBA,DATA_ZLECENIA,NAZWA_TOWARU,CENA)"
							+ "VALUES('"+nazwa+"',"+liczba+",'"+data+"','"+nazwaTowaru+"',"+cena+")");
					JOptionPane.showMessageDialog(frame, "Dodano koszt jednorazowy","",JOptionPane.INFORMATION_MESSAGE);
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
		btnDodajKoszt.setBounds(270, 110, 135, 23);
		frame.getContentPane().add(btnDodajKoszt);
		
		JButton btnUsuKoszt = new JButton("Usu\u0144 koszt");
		btnUsuKoszt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = textFieldId.getText();
				Main.db.SqlCUD("DELETE FROM KLIENCI WHERE(id='"+id+"')");
				JOptionPane.showMessageDialog(frame, "Usuniêto koszt jednorazowy","",JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnUsuKoszt.setBounds(270, 142, 135, 23);
		frame.getContentPane().add(btnUsuKoszt);
		
		JButton btnEdytujKoszt = new JButton("Edytuj koszt");
		btnEdytujKoszt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String nazwa = textFieldNazwa.getText();
					int liczba = Integer.valueOf(textFieldLiczba.getText());
					String nazwaTowaru = textFieldNazwaTowaru.getText();
					int cena = Integer.valueOf(textFieldCena.getText());
					String data=DateTimeFormatter.ofPattern("yy/MM/dd").format(LocalDate.now());
					String id = textFieldId.getText();
					
					Main.db.SqlCUD("UPDATE KOSZTY_JEDNORAZOWE"
							+ " SET NAZWA='"+nazwa+"',LICZBA="+liczba+",'"+data+"','"+nazwaTowaru+"',"+cena+""
					+"WHERE ID='"+id+"'");
					JOptionPane.showMessageDialog(frame, "Zmieniono koszt jednorazowy","",JOptionPane.INFORMATION_MESSAGE);
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
		btnEdytujKoszt.setBounds(270, 172, 135, 23);
		frame.getContentPane().add(btnEdytujKoszt);
		
		JButton btnWywietlKoszty = new JButton("Wy\u015Bwietl koszty");
		btnWywietlKoszty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KsiegowyGUIOptions.WyswietlKosztyJednorazowe.NewScreen();
				frame.dispose();
			}
		});
		btnWywietlKoszty.setBounds(270, 204, 135, 23);
		frame.getContentPane().add(btnWywietlKoszty);
	}
}
