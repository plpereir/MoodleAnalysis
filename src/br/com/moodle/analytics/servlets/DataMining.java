package br.com.moodle.analytics.servlets;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.moodle.analytics.BD.ConnectionFactory;
import br.com.moodle.analytics.ml.ML;
import br.com.moodle.analytics.ml.generateARFF;

/**
 * Servlet implementation class DataMining
 */
public class DataMining extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String TMP_DIR = System.getProperty("java.io.tmpdir") + "/ARFF/";

//	private static String table = "mdl_assign";
	
	private static Properties prop = new Properties();

	public static String getPropertyValue(InputStream in, String key) {
		try {
			prop.load(in);
			return prop.getProperty(key);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}

	public Properties setPropertiesDatabase(InputStream in) {
		Properties properties = new Properties();
		properties.setProperty("user", getPropertyValue(in, "user"));
		properties.setProperty("password", getPropertyValue(in, "password"));
		properties.setProperty("useSSL", getPropertyValue(in, "useSSL"));
		properties.setProperty("useTimezone", getPropertyValue(in, "useTimezone"));
		properties.setProperty("serverTimezone", getPropertyValue(in, "serverTimezone"));
		properties.setProperty("autoReconnect", getPropertyValue(in, "autoReconnect"));

		return properties;
	}
	

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String table = request.getParameter("modules");
		boolean success = (new File(TMP_DIR)).mkdirs();
		InputStream in = getServletContext().getResourceAsStream("/WEB-INF/properties/config.properties");

		if (success) {
			File file = new File(TMP_DIR + table +".arff");
			if (file.delete()) {
				System.out.println("File " + TMP_DIR  + table +".arff File deleted from Project root directory");
			} else {
				System.out.println("File " + TMP_DIR + table +".arff doesn't exist in the project root directory");
			}
		} else {
			File directory = new File(TMP_DIR);
			if (directory.exists()) {
				File file = new File(TMP_DIR + table +".arff");
				if (file.delete()) {
					System.out.println("File " + TMP_DIR + table +".arff File deleted from Project root directory");
				} else {
					System.out.println("File " + TMP_DIR + table +".arff doesn't exist in the project root directory");
				}
			} else {
				System.out.println("Can't create directory/file");
			}
		}

		try {
			generateARFF.getARFFFromFile(ConnectionFactory.getConnection(getPropertyValue(in, "serverName"),
					getPropertyValue(in, "mydatabase"), setPropertiesDatabase(in)),getPropertyValue(in, "mydatabase"),table,TMP_DIR + table +".arff");
			
			ML.Apriori(TMP_DIR,table);
			ML.EM(TMP_DIR,table);
			request.setAttribute("module",table);
			request.setAttribute("pathfile",TMP_DIR);
			} catch (Exception e) {
				request.setAttribute("apriori", e.toString());
				request.setAttribute("EM", e.toString());
		}
		request.getRequestDispatcher("/datamining.jsp").forward(request, response);
	}

}
