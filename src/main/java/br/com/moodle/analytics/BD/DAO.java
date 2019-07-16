package br.com.moodle.analytics.BD;
import java.sql.*;

public class DAO {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{  
				Class.forName("com.mysql.cj.jdbc.Driver");  
				Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/moodleAnalytics","root","League2016");  
			//here sonoo is database name, root is username and password  
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select * from mdl_assign");  
			while(rs.next())  
			System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  
			con.close();  
			}catch(Exception e){ System.out.println(e);}  
	}

}
