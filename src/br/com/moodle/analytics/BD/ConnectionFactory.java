/**
 * 
 */
package br.com.moodle.analytics.BD;
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

import br.com.moodle.analytics.util.GetInformationsProperties;

public class ConnectionFactory {
	/**
	 * properties used this class
	 */
	protected static GetInformationsProperties prop = new GetInformationsProperties();

	/**
	 * this is the connection method
	 */
	public static java.sql.Connection getConnectionMySQL() {
		
		/**
		 * attribute Connection Type
		 */
		Connection connection = null;
			/**
			 * loading standard JDBC driver
			 */
			String driverName = "com.mysql.cj.jdbc.Driver";
			try {
				Class.forName(driverName);
				/**
				 * set config database connection - give property file location - load
				 * properties file - takes the key of the property as a parameter and return the
				 * value to connect database
				 */
						String serverName = prop.getPropertyValue("serverName");
						String mydatabase = prop.getPropertyValue("mydatabase");
						String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
						try {
							connection = DriverManager.getConnection(url, prop.setPropertiesDatabase());
						} catch (SQLException e) {
							e.printStackTrace();
						}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
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
		System.out.println("The connection information: "+conn);
		System.out.println("The connection status: "+getStatusConnection(conn));
		System.out.println("The connection close: "+closeConnection(conn));
	}
}
