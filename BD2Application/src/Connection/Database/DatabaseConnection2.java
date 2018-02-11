package Connection.Database;
import java.sql.*;
public class DatabaseConnection2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			try {
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","MJ","admin");
				Statement st = con.createStatement();
				String sql="select * from PRACOWNICY";
				ResultSet rs=st.executeQuery(sql);
				//while(rs.next())
				//{
				//	System.out.println(rs.getInt(1)+" "+rs.getString(2));
				//}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
