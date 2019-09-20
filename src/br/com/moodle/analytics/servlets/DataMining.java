package br.com.moodle.analytics.servlets;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DataMining
 */
public class DataMining extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String TMP_DIR = System.getProperty("java.io.tmpdir")+"/ARFF/";
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		ServletContext context = getServletContext();
		boolean success = (new File(TMP_DIR)).mkdirs();
		if (success) {
		    // Directory creation
			File file = new File(TMP_DIR+"test.arff");
			if (file.delete()) {
				System.out.println("File "+TMP_DIR+"test.arff File deleted from Project root directory");
			} else
				System.out.println("File "+TMP_DIR+"test.arff doesn't exist in the project root directory");

			PrintWriter writer = new PrintWriter(TMP_DIR+"test.arff", "UTF-8");
			writer.println("@data");
			writer.close();
		}else
		{
			System.out.println("can't create file");
		}
		request.setAttribute("message2", "Created file at path: "+TMP_DIR);
		request.getRequestDispatcher("/datamining.jsp").forward(request, response);
	}

}
