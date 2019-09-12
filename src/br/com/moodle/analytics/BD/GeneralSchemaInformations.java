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
import java.util.Arrays;
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

			String query = ("SELECT COUNT(*) FROM "+ConnectionFactory.prop.getPropertyValue("mydatabase")+"." + table);
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

	public static void getAllInformationsFromTable(String table) throws SQLException {
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
	
	public static ArrayList<String> getColumnListValues(String getTable, String getcolumn) throws SQLException
	{
		ArrayList<String> getcolumnlistalues = new ArrayList<String>();
	    Connection con = ConnectionFactory.getConnectionMySQL();
	    String query = "select " +getcolumn+", count(*) as Qty from "+ConnectionFactory.prop.getPropertyValue("mydatabase")+"."+getTable+" where "+getcolumn+" is not null group by "+getcolumn+" order by Qty desc;";
	  //  String query = "select distinct " +getcolumn+" from BooksAnalytics."+getTable+" order by "+getcolumn+" asc";
	    PreparedStatement st = con.prepareStatement(query);
	    ResultSet rs = st.executeQuery();
	    while(rs.next()){
	    	getcolumnlistalues.add(rs.getString(1));
	    }

		rs.close();
		st.close();
		con.close();
		
		return getcolumnlistalues;
	}
	
	public static ArrayList<String> getTableSelect(String sql) throws SQLException
	{
		ArrayList<String> getSelectRows = new ArrayList<String>();
	    Connection con = ConnectionFactory.getConnectionMySQL();
	    String query = sql;
	    PreparedStatement st = con.prepareStatement(query);
	    ResultSet rs = st.executeQuery();
	    //from result set give metadata
	    ResultSetMetaData rsmd = rs.getMetaData();

	    //columns count from metadata object
	    int numOfCols = rsmd.getColumnCount();
	    while(rs.next()){
	    	String strLine = new String();
	    	for(int i = 1; i <= numOfCols; i++)
	    	{
	    		strLine = strLine +","+ rs.getString(i);
	    	}
    	    System.out.println(strLine.substring(1, strLine.length()));
    	    getSelectRows.add(strLine.substring(1, strLine.length()));
	    }

		rs.close();
		st.close();
		con.close();
		
		return getSelectRows;
	}
	
	public static ArrayList<String> getColumnNames(String getTable) throws SQLException
	{
		ArrayList<String> getcolumnnames = null;
        String module = getTable;
        switch (module) {
            case "mdl_assign":
        		 getcolumnnames = new ArrayList<String>(Arrays.asList(ConnectionFactory.prop.getPropertyValue("assignment_sql").split(",")));
                break;
            default:
            	 getcolumnnames  = new ArrayList<String>();
        	    Connection con = ConnectionFactory.getConnectionMySQL();
        	    String query = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = '"+ConnectionFactory.prop.getPropertyValue("mydatabase")+"' AND TABLE_NAME = '"+getTable+"';";
        	    PreparedStatement st = con.prepareStatement(query);
        	    ResultSet rs = st.executeQuery();
        	    while(rs.next()){
        	    	getcolumnnames.add(rs.getString(1));
        	    }

        		rs.close();
        		st.close();
        		con.close();
        }
		return getcolumnnames;
	}

	
	public static boolean tableExist(Connection conn, String tableName) throws SQLException {
	    boolean tExists = false;
	    try (ResultSet rs = conn.getMetaData().getTables(null, null, tableName, null)) {
	        while (rs.next()) { 
	            String tName = rs.getString("TABLE_NAME");
	            if (tName != null && tName.equals(tableName)) {
	                tExists = true;
	                break;
	            }
	        }
	    }
	    return tExists;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		 * for (String table : getAllTablesFromSchema()) { System.out.println(table); }
		 */
		try {
			getColumnNames("mdl_grading_definitions");
		} catch (SQLException e) {
			System.out.println("Error: "+e.toString());
		}

	}

}
