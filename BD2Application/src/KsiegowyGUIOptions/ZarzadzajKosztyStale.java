package KsiegowyGUIOptions;

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

import Application.KsiegowyGUI;
import Application.LogIn;
import Application.Main;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class ZarzadzajKosztyStale {

	private JFrame frame;
	private JTextField textFieldKategoria;
	private JTextField textFieldWysokoœæ_kosztu;
	private JTextField textFieldId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ZarzadzajKosztyStale window = new ZarzadzajKosztyStale();
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
					ZarzadzajKosztyStale window = new ZarzadzajKosztyStale();
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
	public ZarzadzajKosztyStale() {
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
		
		textFieldKategoria = new JTextField();
		textFieldKategoria.setBounds(123, 80, 120, 30);
		frame.getContentPane().add(textFieldKategoria);
		textFieldKategoria.setColumns(10);
		
		textFieldWysokoœæ_kosztu = new JTextField();
		textFieldWysokoœæ_kosztu.setBounds(123, 125, 120, 30);
		frame.getContentPane().add(textFieldWysokoœæ_kosztu);
		textFieldWysokoœæ_kosztu.setColumns(10);
		
		textFieldId = new JTextField();
		textFieldId.setBounds(123, 166, 120, 30);
		frame.getContentPane().add(textFieldId);
		textFieldId.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Kategoria");
		lblNewLabel.setBounds(36, 88, 72, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("Op\u0142ata");
		lblNewLabel_2.setBounds(42, 133, 51, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblIdopcjonalne = new JLabel("Id(opcjonalne)");
		lblIdopcjonalne.setBounds(24, 174, 87, 14);
		frame.getContentPane().add(lblIdopcjonalne);
		
		JButton btnDodajKoszt = new JButton("Dodaj koszt");
		btnDodajKoszt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String kategoria = textFieldKategoria.getText();
					int oplata = Integer.valueOf(textFieldWysokoœæ_kosztu.getText());
					Main.db.SqlCUD("INSERT INTO KOSZTY_STALE(KATEGORIA,WYSOKOSC_KOSZTU)"
							+ " VALUES('"+kategoria+"',"+oplata+")");
					JOptionPane.showMessageDialog(frame, "Dodano koszt sta³y","",JOptionPane.INFORMATION_MESSAGE);
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(frame, "Operacja nie powiod³a siê","",JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnDodajKoszt.setBounds(293, 104, 131, 23);
		frame.getContentPane().add(btnDodajKoszt);
		
		JButton btnUsuKoszt = new JButton("Usu\u0144 koszt");
		btnUsuKoszt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String id = textFieldId.getText();
				boolean logic = false;
				
				ResultSet res = Main.db.SqlREAD("SELECT ID FROM KOSZTY_STALE");
				try {
					while(res.next())
					{
						if(id.equals(Integer.valueOf(res.getInt(1))))	
						{
							logic = true;
						}
						
					}
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				
				
					
					if(id.isEmpty())
						JOptionPane.showMessageDialog(frame, "Operacja nie powiod³a siê","",JOptionPane.ERROR_MESSAGE);
					else
					{
						if(logic)
						{
							Main.db.SqlCUD("DELETE FROM KOSZTY_STALE WHERE(id='"+id+"')");
							JOptionPane.showMessageDialog(frame, "Usuniêto koszt sta³y","",JOptionPane.INFORMATION_MESSAGE);
						}
						else
							JOptionPane.showMessageDialog(frame, "Operacja nie powiod³a siê","",JOptionPane.ERROR_MESSAGE);
						
					}
					
				
			}
		});
		btnUsuKoszt.setBounds(293, 135, 131, 23);
		frame.getContentPane().add(btnUsuKoszt);
		
		JButton btnEdytujKoszt = new JButton("Edytuj koszt");
		btnEdytujKoszt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String kategoria = textFieldKategoria.getText();
					int oplata = Integer.valueOf(textFieldWysokoœæ_kosztu.getText());
					String id = textFieldId.getText();
					Main.db.SqlCUD("UPDATE KOSZTY_STALE"
							+ " SET KATEGORIA='"+kategoria+"',WYSOKOSC_KOSZTU="+oplata+""
					+"WHERE ID='"+id+"'");
					JOptionPane.showMessageDialog(frame, "Zmieniono Klienta","",JOptionPane.INFORMATION_MESSAGE);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(frame, "Operacja nie powiod³a siê","",JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				} catch (HeadlessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnEdytujKoszt.setBounds(293, 165, 131, 23);
		frame.getContentPane().add(btnEdytujKoszt);
		
		JButton btnWywietlKoszty = new JButton("Wy\u015Bwietl koszty");
		btnWywietlKoszty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KsiegowyGUIOptions.WyswietlKosztyStale.NewScreen();
				frame.dispose();
			}
		});
		btnWywietlKoszty.setBounds(293, 193, 131, 23);
		frame.getContentPane().add(btnWywietlKoszty);
	}
}
