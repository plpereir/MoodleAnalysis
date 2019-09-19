package br.com.moodle.analytics.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.moodle.analytics.BD.ConnectionFactory;
import br.com.moodle.analytics.BD.GeneralSchemaInformations;

public class LoadDashboard extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9109500626878221408L;
	
	public String getMoodleModuleAnalysis(Connection conn, InputStream in) {
		String tmpString = "";
		InputStream f = null;

		if (in != null)
		{
			f = in;
		}
		for (String module : getPropertyValue(f,"modules").split(",")) {
			try
			{
				float rYes = 0;
				float rNo = 0;

			for (String table : getPropertyValue(f,module + "_table").split(",")) {
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
				tmpString = tmpString +("<b>Moodle Analysis:</b> " + module);
				tmpString = tmpString +("The module <b>"+module+"</b> is not use.");
				tmpString = tmpString +("When this module is not use, you left of the use the follow functions: </b>"+getPropertyValue(f,module + "_text")+"</b>");
			}else
			{
				
				tmpString = tmpString +("<BR><b>Moodle Analysis:</b> " + module+"<BR>");
				tmpString = tmpString + ("<b>The module </b>"+module+" is use with: <b>"+((rYes / (rYes + rNo)) * 100)+"</b> percent of the capacity");
			}
				

			}catch (Exception ex)
			{
				//System.out.println("The property is not existis: "+module + "_table");
			}
		}
		return tmpString;
	}
	
	private static Properties prop = new Properties();	
	
	public String getPropertyValue(InputStream in, String key)
	{
		try {
		prop.load(in);
		return prop.getProperty(key);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}
	
	public Properties setPropertiesDatabase(InputStream in)
	{
		Properties properties = new Properties();
		properties.setProperty("user", getPropertyValue(in,"user"));
		properties.setProperty("password", getPropertyValue(in,"password"));
		properties.setProperty("useSSL", getPropertyValue(in,"useSSL"));
		properties.setProperty("useTimezone", getPropertyValue(in,"useTimezone"));
		properties.setProperty("serverTimezone", getPropertyValue(in,"serverTimezone"));
		properties.setProperty("autoReconnect", getPropertyValue(in,"autoReconnect"));
		
		return properties;
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		InputStream in = getServletContext().getResourceAsStream("/WEB-INF/properties/config.properties");
		
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<script src=\"bootstrap-4.3.1-dist/js/bootstrap.min.js\"></script>");
		out.println("<link rel=\"stylesheet\" href=\"bootstrap-4.3.1-dist/css/bootstrap.min.css\">");
		out.println("<title>Primeira Servlet</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<div class=\"container\">");
		out.println("<h1>Modules:</h1>");

		try {
			out.println(getMoodleModuleAnalysis(ConnectionFactory.getConnection(getPropertyValue(in, "serverName"), getPropertyValue(in, "mydatabase"), setPropertiesDatabase(in)),in));

		} catch (Exception e) {
			out.println("Error load informations: "+e.toString());
		}
		
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
	}
}
