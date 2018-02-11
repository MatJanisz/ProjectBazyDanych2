package Application;
import java.sql.ResultSet;
import java.sql.SQLException;
import Connection.Database.*;

public class Main { //do usuniecia

	public static String imie="ala"; 
	public static String nazwisko="bvc";
	public static DatabaseConnection db = new DatabaseConnection("sklep","admin");
	public static int userId;
	public static boolean operationStatus = true;
	
	public static void main(String[] args) {
		//DatabaseConnection db = new DatabaseConnection("MJ","admin");
		//db.SqlCUD("DELETE FROM KLIENCI WHERE ID=5");
		
		/*ResultSet res = db.SqlREAD("SELECT * FROM DANE_LOGOWANIA");
		
		try {
			while(res.next())
			{
				System.out.println(res.getInt(1)+" "+res.getString(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		new LogIn();
		
	}

}
