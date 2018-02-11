package Application;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountFactory {
	
	public static void ProduceAccount(int id)
	{
		String stanowisko="";
		ResultSet res = Main.db.SqlREAD("SELECT STANOWISKO FROM PRACOWNICY WHERE ID="+id+"");
		try {
			while(res.next())
			{
				stanowisko = res.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(stanowisko.equals("wlasciciel"))
			 new WlascicielGUI().NewScreen();
		else if(stanowisko.equals("ksiegowy"))
			 new KsiegowyGUI().NewScreen();
		else if(stanowisko.equals("sprzedawca"))
			 new SprzedawcaGUI().NewScreen();
		else if(stanowisko.equals("magazynier"))
			 new MagazynierGUI().NewScreen();
	}

}
