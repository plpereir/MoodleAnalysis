package br.com.moodle.analytics.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.moodle.analytics.BD.ConnectionFactory;
import br.com.moodle.analytics.BD.GeneralSchemaInformations;

/**
 * Servlet implementation class AnalysisActiveModules
 */
public class AnalysisActiveModules extends HttpServlet {

	public static String getMoodleModuleAnalysis(Connection conn, InputStream f, HttpServletRequest request,HttpServletResponse response, ServletContext sc) throws IOException 
	{
		
		//set cookies variables
		Cookie cookie = null;
		Cookie[] cookies = null;

		/*
		English = false;
		Portuguese = true;
		*/
		Boolean setLang = false;
		// Get an array of Cookies associated with the this domain
		cookies = request.getCookies();

		if (request.getParameter("lg") != null) {
			if (request.getParameter("lg").compareTo("PT") == 0) {
				setLang = true;
			}
			// Create cookies for first and last names.      
			Cookie newcookie = new Cookie("moodleAnalyticsLanguage", request.getParameter("lg"));

			// Set expiry date after 24 Hrs for both the cookies.
			newcookie.setMaxAge(60 * 60 * 24);

			// Add both the cookies in the response header.
			response.addCookie(newcookie);

		} else {
			if (cookies != null) {
				for (int i = 0; i < cookies.length; i++) {
					cookie = cookies[i];
					if (cookie.getName().compareTo("moodleAnalyticsLanguage") == 0) {
						if (cookie.getValue().compareTo("PT") == 0) {
							setLang = true;
						}
					}
				}
			}
		}
		//check get parameter
		String tmpLg = "";
		if (setLang) {
			//read file type config
			tmpLg = "/WEB-INF/properties/PTlang.properties";
		} else {
			tmpLg = "/WEB-INF/properties/ENlang.properties";
		}
		//set properties, and load properties
		InputStream inLg = sc.getResourceAsStream(tmpLg);
		Properties propLg = new Properties();
		propLg.load(inLg);
		
		String tmpString = "[";
		String report = "	<div class='alert alert-warning' role='alert'>";
		report = report + propLg.getProperty("analysisActiveModuleInformations");
		report = report + "</div><div id='report'>";

		for (String module : getPropertyValue(f, "modules").split(",")) {
			try {
				float rYes = 0;
				float rNo = 0;

				for (String table : getPropertyValue(f, module + "_table").split(",")) {
					try {
						if (GeneralSchemaInformations.tableExist(conn, "mdl_" + table)) {
							rYes++;
						} else {
							rNo++;
						}
					} catch (SQLException e) {
						System.out.println("Erro Table existis: " + e.toString());
					}
				}

				if (rYes == 0) {
					tmpString = tmpString + "0.0,";

					report = report + "<div><p>"+propLg.getProperty("themodule")+"<b> "+propLg.getProperty(module)+" </b>"+propLg.getProperty("isnotuse")+".</p>";
					report = report
							+ "<p>"+propLg.getProperty("whenthismodule")+"<b>"+propLg.getProperty("isnotuse")+"</b>, "+propLg.getProperty("followfunctions")+": <b>"
							+ propLg.getProperty(module + "_text") + "</b></p></div><hr>|";

					System.out.println("Moodle Analysis: " + module);
					System.out.println("The module " + module + " is not use.");
					System.out.println("When this module is not use, you left of the use the follow functions: "
							+ propLg.getProperty(module + "_text"));
				} else {
					tmpString = tmpString + ((rYes / (rYes + rNo)) * 100) + ",";
					System.out.println("Moodle Analysis: " + module);
					System.out.println("The module " + module + " is use with: " + ((rYes / (rYes + rNo)) * 100)
							+ " percent of the capacity");

				}

			} catch (Exception ex) {
				// System.out.println("The property is not existis: "+module +
				// "_table");
			}
		}
		tmpString = tmpString + "]";
		System.out.println(tmpString.replace(",]", "]" + "|" + report));
		return tmpString.replace(",]", "]" + "|" + report);
	}

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

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ServletContext sc = getServletContext();
		
		InputStream in = getServletContext().getResourceAsStream("/WEB-INF/properties/config.properties");

		String tmp = getMoodleModuleAnalysis(ConnectionFactory.getConnection(getPropertyValue(in, "serverName"),
				getPropertyValue(in, "mydatabase"), setPropertiesDatabase(in)), in, request, response,sc);
		String message1 = tmp.substring(0, tmp.indexOf("|"));
		String message2 = tmp.substring(tmp.indexOf("|")+1 , tmp.lastIndexOf("|")-1 );
		request.setAttribute("message1", message1);
		request.setAttribute("message2", message2);
		request.getRequestDispatcher("/Diagnostic.jsp").forward(request, response);
	}

}
