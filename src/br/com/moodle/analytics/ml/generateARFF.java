package br.com.moodle.analytics.ml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.moodle.analytics.BD.ConnectionFactory;
import br.com.moodle.analytics.BD.GeneralSchemaInformations;

public class generateARFF {


	
	public static void getARFFFromFile(Connection conn,String schema, String table,String pathFile) throws Exception, Exception {
		try {
			if (GeneralSchemaInformations.isContainsRecords(conn, schema, table))
			{
			File file = new File(pathFile);
			if (file.delete()) {
				System.out.println(pathFile + " File deleted from Project root directory");
			} else
				System.out.println(pathFile + " doesn't exist in the project root directory");

			PrintWriter writer = new PrintWriter(pathFile, "UTF-8");
			
			try {
				ArrayList<String> listColumns  = GeneralSchemaInformations.getColumnNames(conn,schema,table);
				String queryFields = "";
				System.out.println("@relation "+table);
				writer.println("@relation "+table);
				for (String element : listColumns)
				{
						ArrayList<String> listFieldValues = GeneralSchemaInformations.getColumnListValues(conn,schema,table,element);
						String attribute = new String("@attribute "+element+" {");
						for (String values : listFieldValues)
						{
							attribute = attribute + values + ",";
						}
						attribute = attribute.substring(0, attribute.length()) + "}";
						attribute = attribute.replace(",}", "}");
						writer.println(attribute);
						System.out.println(attribute);
						queryFields += element+",";
				}
				queryFields = queryFields.substring(0, queryFields.length()-1);
				writer.println("@data");
				System.out.println("@data");
				
				ArrayList<String> listFieldValues = GeneralSchemaInformations.getTableSelect(conn,"select "+queryFields+" from "+schema+"."+table);
				for (String values : listFieldValues)
				{
					writer.println(values);
					System.out.println(values);
				}
				
				writer.close();
			} catch (SQLException e) {
				System.out.println(e.toString());	
			}

			writer.close();
			}

		} catch (FileNotFoundException e) {
			System.out.println(e.toString());			
		} catch (UnsupportedEncodingException e) {
			System.out.println(e.toString());	
		}
	}

	public static void main(String[] args) {
		try {
			getARFFFromFile(ConnectionFactory.getConnectionMySQL(),ConnectionFactory.prop.getPropertyValue(null,"mydatabase"),"mdl_assign","arff/mdl_assign.arff");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
