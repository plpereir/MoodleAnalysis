package br.com.moodle.analytics.BD;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import br.com.moodle.analytics.util.GetInformationsProperties;

/*
 * This class has the proposal realize the diagnostic about database, the main goals are:
 * - get percent tables about schema
 * - get inactive Moodle modules
 * - provide informations about active Moodle modules to arff files;
 * - provide informations about active Moodle modules to dash boards tree;
 */

public class AnalysisDatabase {

	protected static GetInformationsProperties prop = new GetInformationsProperties();

	public static float getGeneralMoodleUse(Connection conn) throws SQLException, Exception {
		float rYes = 0;
		float rNo = 0;

		for (String table : GeneralSchemaInformations.getAllTablesFromSchema(conn)) {
			if (GeneralSchemaInformations.isContainsRecords(conn, table) == true) {
				rYes = rYes + 1;
			} else {
				rNo = rNo + 1;
			}
		}
		return ((rYes / (rYes + rNo)) * 100);
	}

	public static String getMoodleModuleAnalysis(Connection conn, InputStream in) {
		String tmpString = "";
		InputStream f = null;

		if (in != null)
		{
			f = in;
		}
		
		
		for (String module : prop.getPropertyValue(f,"modules").split(",")) {
			try
			{
				float rYes = 0;
				float rNo = 0;

			for (String table : prop.getPropertyValue(f,module + "_table").split(",")) {
				try {
					if (GeneralSchemaInformations.tableExist(conn, "mdl_" + table)) {
						rYes ++;
					} else {
						rNo ++;
					}
				} catch (SQLException e) {
					System.out.println("Erro Table existis: "+e.toString());
				}
			}

			if (rYes == 0)
			{
				System.out.println("Moodle Analysis: " + module);
				System.out.println("The module "+module+" is not use.");
				System.out.println("When this module is not use, you left of the use the follow functions: "+prop.getPropertyValue(f,module + "_text"));
			}else
			{
				tmpString += "<p>"+module+":</p>";
				tmpString += "<div class='progress'>";
				tmpString += "<div class='progress-bar progress-bar-success' role='progressbar' aria-valuenow="+((rYes / (rYes + rNo)) * 100)+" aria-valuemin='0' aria-valuemax='100' style='width:"+((rYes / (rYes + rNo)) * 100)+"%'>";
				tmpString += "<div class='progress'>";
				tmpString += ((rYes / (rYes + rNo)) * 100)+"% in use";
				tmpString += "</div>";

				
				
				System.out.println("Moodle Analysis: " + module);
				System.out.println("The module "+module+" is use with: "+((rYes / (rYes + rNo)) * 100)+" percent of the capacity");
				
			}
				
			}catch (Exception ex)
			{
				//System.out.println("The property is not existis: "+module + "_table");
			}
		}
		return tmpString;
	}

	public static void main(String[] args) {
		try {
			Connection conn = ConnectionFactory.getConnectionMySQL();
			//System.out.println("This moodle instance use " + getGeneralMoodleUse(conn) + "%");
			getMoodleModuleAnalysis(conn,null);
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
