package functions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

//This is the code what can search local SQL file and Find file is exist or not
//Return 1: Found file in SQL DB.
//Return 0: Cannot found.
//Return -: Something problem in SQL

public class SQL {
	
	private String query = null;
	private String currDB = null;
	private short returnValue = 0;
	private java.sql.Statement st = null;
	private ResultSet rs = null;
	
	public SQL(String databaseName){
		currDB = databaseName;
		Connection con = null;
		try{
			con = DriverManager.getConnection("jdbc:mysql://localhost", "root", "12345");
			st = con.createStatement();
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
			System.out.println(e.getSQLState());
		}
		
	}
	
	
	
	public String getResult(String queryInput){
		
		query = queryInput;
		String result = "";
		
		try{
			do{
				result += rs.getString(1);// + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4);
				result += "\n";
			}
			while(rs.next());
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
			System.out.println(e.getSQLState());
		}
		
		return result;
	}
	
	
	
	public int isFileAvailable(String queryInput){
		
		query = queryInput;
		
		try{
			
			st.execute("use " + currDB);

			rs = st.executeQuery(query);
			
			if(!rs.first())
				return 0;
			return 1;
			
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
			System.out.println(e.getSQLState());
			return -1;
		}
		
	}
	
}
