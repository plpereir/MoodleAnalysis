/**
 * 
 */
package br.com.moodle.analytics.BD;

/**
 * @author plpereir
 *
 */
public class InformationsTable {

	private String schema;
	private String table;
	/**
	 * @return the schema
	 */
	protected String getSchema() {
		return schema;
	}
	/**
	 * @param schema the schema to set
	 */
	protected void setSchema(String schema) {
		this.schema = schema;
	}
	/**
	 * @return the table
	 */
	protected String getTable() {
		return table;
	}
	/**
	 * @param table the table to set
	 */
	protected void setTable(String table) {
		this.table = table;
	}
	
	
}
