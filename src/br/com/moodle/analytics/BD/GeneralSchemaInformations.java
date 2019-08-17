/**
 * 
 */
package br.com.moodle.analytics.BD;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Pedro
 * The proposal this class is provide relevant informations
 * about the schema, for example return the list of modules used this 
 * moodle instance
 */
public class GeneralSchemaInformations {

	ConnectionFactory cf = new ConnectionFactory();
	
	
	private List<String> getAllTablesFromSchema()
	{
		List<String> tables = new ArrayList<>();
		return tables;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Matheus GamerPro");
	}

}
