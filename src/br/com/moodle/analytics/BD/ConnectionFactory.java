/**
 * 
 */
package br.com.moodle.analytics.BD;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

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
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
	/**
	 * properties used this class
	 */
	private static Properties prop = new Properties();

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
	public static boolean getStatusConnection(Connection conn) {
		if (conn == null)
		{
			return false;
		}else
		{
			return true;
		}
		
	}

	/**
	 * This method closed the connection
	 */
	public static boolean closeConnection(Connection conn) {
		try {
			conn.close();
			return true;
		} catch (SQLException e) {
			System.out.println("Error close Connection: "+e);
			return false;
		}
	}

	/**
	 * This method restart the connection
	 */
	public java.sql.Connection restartConnection(Connection conn) {
		closeConnection(conn);
		return getConnectionMySQL();
	}

	/**
	 * @param args
	 * @throws SQLException
	 */
	public static void main(String[] args) throws SQLException {
		Connection conn = getConnectionMySQL();

		// TODO Auto-generated method stub
		System.out.println("The connection information: "+conn);
		System.out.println("The connection status: "+getStatusConnection(conn));
		System.out.println("The connection close: "+closeConnection(conn));
	}
}
