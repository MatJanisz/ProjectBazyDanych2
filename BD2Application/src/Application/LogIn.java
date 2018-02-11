package Application;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class LogIn {
	private JTextField textField;
	private JPasswordField passwordField;

	//private JFrame frame;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public LogIn() {
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		JFrame frame = new JFrame();
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
		
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblSystemLogowania = new JLabel("System Logowania");
		lblSystemLogowania.setHorizontalAlignment(SwingConstants.CENTER);
		lblSystemLogowania.setBounds(158, 11, 136, 54);
		frame.getContentPane().add(lblSystemLogowania);
		
		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLogin.setBounds(95, 79, 57, 25);
		frame.getContentPane().add(lblLogin);
		
		JLabel lblHaso = new JLabel("Has\u0142o:");
		lblHaso.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHaso.setBounds(84, 113, 67, 25);
		frame.getContentPane().add(lblHaso);
		
		textField = new JTextField();
		textField.setBounds(174, 78, 106, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(174, 112, 106, 26);
		frame.getContentPane().add(passwordField);
		
		JButton btnZaloguj = new JButton("Zaloguj");
		btnZaloguj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String login = textField.getText();
				String password = new String(passwordField.getPassword());
				
				boolean validation = false;
				ResultSet res = Main.db.SqlREAD("SELECT * FROM DANE_LOGOWANIA");
				
				try {
					while(res.next())
					{
						int DatabaseID = res.getInt(1);
						String DatabaseLogin = res.getString(2);
						String DatabasePassword = res.getString(3);
						if(DatabaseLogin.equals(login) && DatabasePassword.equals(password))
						{
							validation = true;
							Main.userId=DatabaseID;
							JOptionPane.showMessageDialog(frame, "Zalogowano","",JOptionPane.INFORMATION_MESSAGE);
							break;
						}
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(validation==false)
					JOptionPane.showMessageDialog(frame, "Login lub has³o jest nieprawid³owe","B³¹d",JOptionPane.ERROR_MESSAGE);
				else
				{
					AccountFactory.ProduceAccount(Main.userId);
					frame.dispose();
					
				}
				
			}
		});
		btnZaloguj.setBounds(113, 162, 89, 23);
		frame.getContentPane().add(btnZaloguj);
		
		JButton btnWyjd = new JButton("Wyj\u015Bcie");
		btnWyjd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnWyjd.setBounds(227, 162, 89, 23);
		frame.getContentPane().add(btnWyjd);
		
		frame.setVisible(true);
	}
}
