/**
 * 
 */
package br.com.moodle.analytics.BD;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Pedro Luiz da Silva Pereira
 * date: 2019 05 16
 * proposal: this class is responsible to manager database interactions: 
 * connection, select, update, delete, relations etc.
 */

/**
 * required class to manager database
 */
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DAO {
	/**
	 * properties used this class
	 */
	private static Properties prop = new Properties();

	/**
	 * this is the constructor class
	 */
	public DAO() {

	}

	/**
	 * this attribute return connection
	 */
	public static Boolean Status = false;

	/**
	 * this is the connection method
	 */
	public static java.sql.Connection getConnectionMySQL() {
		
		/**
		 * attribute Connection Type
		 */
		Connection connection = null;
		try {

			/**
			 * loading standard JDBC driver
			 */
			String driverName = "com.mysql.cj.jdbc.Driver";
			Class.forName(driverName);

			/**
			 * set config database connection - give property file location - load
			 * properties file - takes the key of the property as a parameter and return the
			 * value to connect database
			 */
			FileInputStream ip;
			try {
				ip = new FileInputStream("config.properties");
				try {
					prop.load(ip);
					String serverName = prop.getProperty("serverName");
					String mydatabase = prop.getProperty("mydatabase");
					String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
					
					Properties properties = new Properties();
					properties.setProperty("user", prop.getProperty("user"));
					properties.setProperty("password", prop.getProperty("password"));
					properties.setProperty("useSSL", prop.getProperty("useSSL"));
					properties.setProperty("useTimezone", prop.getProperty("useTimezone"));
					properties.setProperty("serverTimezone", prop.getProperty("serverTimezone"));
					properties.setProperty("autoReconnect", prop.getProperty("autoReconnect"));
					
					connection = DriverManager.getConnection(url,properties);

					/**
					 * Test connection
					 */
					if (connection != null) {
						Status = true;
					} else {
						Status = false;
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			System.out.println("The specified driver could not be found: "+e);
			connection = null;
		} catch (SQLException e) {
			System.out.println("Cloud not connect database: "+e);
			connection = null;
		}
		return connection;
	}

	/**
	 * This method return connection status
	 */
	public boolean statusConnection() {
		return Status;
	}

	/**
	 * This method closed the connection
	 */
	public static boolean closeConnection() {
		try {
			DAO.getConnectionMySQL().close();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	/**
	 * this method get all tables from informed schema.
	 */
	public static ArrayList<InformationsSchema> getAllTables(Connection conn) {
		ArrayList<InformationsSchema> listInformationsSchema = new ArrayList<InformationsSchema>();
		DatabaseMetaData md;
		try {
			md = conn.getMetaData();
			ResultSet rs = md.getTables(null, null, "%", null);
			while (rs.next()) {
				InformationsSchema is = new InformationsSchema();

				try {
					is.setSchema(rs.getString(1));
					is.setObjectName(rs.getString(3));
					is.setObjectType(rs.getString(4));
					if (!(is.getSchema().equals("information_schema")) && (is.getObjectType().equals("TABLE"))) {
						listInformationsSchema.add(is);
					}
				} catch (Exception ex) {

				}

			}
			rs.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listInformationsSchema;
	}

	/**
	 * this method return all columns from table/schema
	 * 
	 * @throws SQLException
	 */
	public static List<String> getColumnsTables(Connection conn, String schema, String table) throws SQLException {

		List<String> listColumns = new ArrayList<String>();
		
		conn = getConnectionMySQL();

		// --- LISTING DATABASE COLUMN NAMES ---
		DatabaseMetaData meta = conn.getMetaData();
		ResultSet resultSet = meta.getColumns(schema, null, table, "%");
		while (resultSet.next()) {
			try {
				listColumns.add(resultSet.getString(4));
				//System.out.println("Column Name of table " + table + " = " + resultSet.getString(4));
			} catch (Exception ex) {

			}
		}
		return listColumns;
	}

	/**
	 * this method return SQL Query from table/schema
	 * 
	 * @throws SQLException
	 */
	public static String getSQLQyery(Connection conn, String schema, String table) throws SQLException {
		conn = getConnectionMySQL();

		// --- LISTING DATABASE COLUMN NAMES ---
		DatabaseMetaData meta = conn.getMetaData();
		ResultSet resultSet = meta.getColumns(schema, null, table, "%");
		String query = "SELECT ";

		while (resultSet.next()) {
			try {
				query += table + "." + resultSet.getString(4);
				query += ", ";
			} catch (Exception ex) {
			}
		}
		query += " FROM " + table + ";";
		query = query.replace(",  FROM ", "  FROM ");
		return query;
	}

	/**
	 * This method return report informations about tables with data or without
	 * data.
	 */
	public static boolean checkDataExistis(Connection conn, String schema, String table) {
		try {
			String sql = "SELECT count(*) as Qty FROM " + table;
			Statement stm = conn.createStatement();
			ResultSet resultado = stm.executeQuery(sql);
			if (resultado != null && resultado.next()) {
				//System.out.println(table+" - "+resultado.getString("Qty"));
				if (resultado.getString("Qty").equals("0"))
				{
					return false;
				} else {
					System.out.println(table);
					return true;
				}
			} else {
				return false;
			}
		} catch (SQLException ex) {
			System.out.println("Error checkData Existis: "+ex);
			//ex.printStackTrace();
			return false;
		}

	}
	
	/**
	 * this method create new Table
	 */
	private static void createTable( Connection conn,  String schema) {
	    String myTableName = "Alter TABLE GlobalInformations ADD" 
	            + " testecoluna BIGINT(10)";
	    		
	           // + "initials SMALLINT(4)," 
	           // + "txt VARCHAR(25),"
	           // + "agentDate DATE,"  
	           // + "agentCount INT(64), "
	           // + "PRIMARY KEY(idNo))";  
	    try {
	    	Statement stmt  = conn.createStatement();
	        //This line has the issue
	    	stmt.executeUpdate(myTableName);
	        System.out.println("Table Created");
	    }
	    catch (SQLException e ) {
	        System.out.println("An error has occured on Table Creation");
	        e.printStackTrace();
	    }
	}
	/**
	 * This method return data all Columns from table and schema
	 */
	public static void getDataFromTable(Connection conn, String schema, String table)
	{
		try {
			PrintWriter writer = new PrintWriter("/Users/plpereir/OneDrive/MacBook/03-Treinamentos/Labs/Java/workspace/MoodleAnalytics/csvFilesFromTables/"+table+".csv", "UTF-8");

			String query = getSQLQyery(conn, schema, table);
	        Statement stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery(query);
	        ResultSetMetaData rsmd = rs.getMetaData();
	          //Get number of columns returned
	          int numOfCols = rsmd.getColumnCount();
	          System.out.println("this table "+table+" has "+numOfCols+" columns.");
	        List<String> listColumns = getColumnsTables(conn, schema, table);
        	String tmp = "";
	          //Print out type for each column
	          for(int i=1; i<=numOfCols; ++i)
	          {
	               System.out.println("Column [" + rsmd.getColumnName(i) + "] data type: " + rsmd.getColumnTypeName(i) + " size: "+rsmd.getColumnDisplaySize(i));
	               tmp += rsmd.getColumnName(i)+"; ";
	          }
	          System.out.println(tmp);
	          writer.println(tmp);
	        
	        while (rs.next())
	        {
	        	tmp = "";
	        	for (String column : listColumns) {

	        		try {
	        			tmp += rs.getInt(column)+"; ";
	        		}catch (Exception ex)
	        		{
	        			try
	        			{
	        				tmp += rs.getString(column)+"; ";
	        			}catch (Exception ex2)
	        			{
	        				System.out.println(ex2);
	        			}
	        		}
	        		
	        	}
	        	System.out.println(tmp);
		        writer.println(tmp);

	        }
			writer.close();
			rs.close();
			stmt.close();
	        
		}catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	/**
	 * This method restart the connection
	 */
	public java.sql.Connection restartConnection() {
		closeConnection();
		return DAO.getConnectionMySQL();
	}

	/**
	 * this method return all tables from schema
	 * @param con
	 * @param schema
	 * @param table
	 */
	public static void getSchemaInformations(Connection conn, String schema)
	{
		List<String> with = new ArrayList<String>();
		List<String> without = new ArrayList<String>();

		for (InformationsSchema item : getAllTables(conn)) {
			try {
				if (checkDataExistis(conn, item.getSchema(), item.getObjectName())
						&& (item.getSchema().equals(schema))) {
					with.add(item.getObjectName());
				} else {
					without.add(item.getObjectName());
				}
			} catch (Exception ex) {

			}
		}
		System.out.println("Tables without data list:");
		for (String out:without)
		{
			System.out.println(out);
		}
		
		System.out.println("Tables with data list:");
		for (String in:with)
		{
			System.out.println(in);
		}
	}
	
	/**
	 * @param args
	 * @throws SQLException
	 */
	public static void main(String[] args) throws SQLException {
		Connection conn = DAO.getConnectionMySQL();

		// TODO Auto-generated method stub
		// System.out.println(DAO.getConnectionMySQL());
		// System.out.println(DAO.statusConnection());
		// DAO.getColumnsTables(DAO.getConnectionMySQL(),item.getSchema(),item.getObjectName());
		// getSQLQyery(conn, "MoodleAnalytics", "mdl_assign_user_flags");
		// getDataFromTable(conn, "MoodleAnalytics", "mdl_assign_user_flags");
		// createTable( conn,  "MoodleAnalytics");
		getSchemaInformations(conn, "MoodleAnalytics");


	}
}
