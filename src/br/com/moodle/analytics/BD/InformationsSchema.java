/**
 * 
 */
package br.com.moodle.analytics.BD;

/**
 * @author Pedro Luiz da Silva Pereira
 * this class is responsible by abstract informations from schema database.
 * the main proposal is management informations about schema as tables, and views.
 */
public class InformationsSchema {
	private String schema;
	private String objectName;
	private String objectType;

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
	 * @return the objectName
	 */
	protected String getObjectName() {
		return objectName;
	}
	/**
	 * @param objectName the objectName to set
	 */
	protected void setObjectName(String objectName) {
		this.objectName = objectName;
	}
	/**
	 * @return the objectType
	 */
	protected String getObjectType() {
		return objectType;
	}
	/**
	 * @param objectType the objectType to set
	 */
	protected void setObjectType(String objectType) {
		this.objectType = objectType;
	}

}
