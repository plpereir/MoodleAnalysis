/**
 * 
 */
package br.com.moodle.analytics.BD;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Pedro The proposal this class is provide relevant informations about
 *         the schema, for example return the list of modules used this moodle
 *         instance
 */
public class GeneralSchemaInformations {

	protected static List<String> getAllTablesFromSchema(Connection conn) {
		List<String> tables = new ArrayList<>();

		DatabaseMetaData md;
		try {
			md = conn.getMetaData();
			ResultSet rs = md.getTables(null, null, "%", null);
			while (rs.next()) {
				if (ConnectionFactory.prop.getPropertyValue("mydatabase").equals(rs.getString(1))
						&& rs.getString(4).equals("TABLE")) {
					tables.add(rs.getString(3));
				}
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tables;
	}

	public static Boolean isContainsRecords(Connection conn, String table) {
		Boolean contains = false;
		try {

			String query = ("SELECT COUNT(*) FROM " + table);
			PreparedStatement cnt = conn.prepareStatement(query);
			ResultSet ct = cnt.executeQuery();
			while (ct.next()) {
				System.out.println("Table " + table + " has " + ct.getString(1) + " records\r\n");
				if (Integer.parseInt(ct.getString(1)) > 0) {
					contains = true;
				} else {
					contains = false;
				}
			}
			ct.close();
			cnt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return contains;
	}

	public static void getColumnNames(String table) throws SQLException {
		DatabaseMetaData meta = ConnectionFactory.getConnectionMySQL().getMetaData();
		ResultSet res = meta.getColumns(ConnectionFactory.prop.getPropertyValue("mydatabase"), null, table, "%");
		System.out.println("List of columns: ");
		while (res.next()) {
			System.out.println("  " + res.getString("TABLE_SCHEM") + ", " + res.getString("TABLE_NAME") + ", "
					+ res.getString("COLUMN_NAME") + ", " + res.getString("TYPE_NAME") + ", "
					+ res.getInt("COLUMN_SIZE") + ", " + res.getInt("NULLABLE"));
		}
		res.close();

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		 * for (String table : getAllTablesFromSchema()) { System.out.println(table); }
		 */
		try {
			Connection conn = ConnectionFactory.getConnectionMySQL();
			getColumnNames("mdl_assign");
			System.out.println(isContainsRecords(conn,"mdl_assign"));
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
