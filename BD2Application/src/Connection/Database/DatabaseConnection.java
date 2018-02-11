package Connection.Database;

import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Application.Main;

public class DatabaseConnection 
{
	public Connection connection; //ustanowienie polaczenia z baza
	
	public DatabaseConnection(String userName,String password) 
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			try {
				Connection connection=DriverManager
						.getConnection("jdbc:oracle:thin:@localhost:1521:xe",userName,password);
				sqlStatement = connection.createStatement();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Statement sqlStatement;
	public ResultSet SqlREAD(String query) //select
	{
		ResultSet result = null;
		try {
			result=sqlStatement.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public void SqlCUD(String query)// create, update, delete
	{
		try {
			ResultSet result=sqlStatement.executeQuery(query);
			Main.operationStatus = true;
		} catch (Exception e) {
			JFrame frame = new JFrame();
			JOptionPane.showMessageDialog(frame, "Operacja nie powiod³a siê","",JOptionPane.ERROR_MESSAGE);
			Main.operationStatus = false;
			e.printStackTrace();
		}
	}
	
	
}
