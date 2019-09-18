package br.com.moodle.analytics.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AnalysisActiveModules
 */
public class AnalysisActiveModules extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String message = null;

		message = "You are not the valid user...";

		request.setAttribute("message", message);
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

}
