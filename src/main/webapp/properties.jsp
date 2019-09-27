<%@ page isELIgnored="false"%>
<%@ page language="java" import="java.util.Properties"%>
<%@ page language="java" import="java.io.InputStream"%>
	<%
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
		String tmp = "";
		if (setLang) {
			//read file type config
			tmp = "/WEB-INF/properties/PTlang.properties";
		} else {
			tmp = "/WEB-INF/properties/ENlang.properties";
		}
		//set properties, and load properties
		InputStream in = getServletContext().getResourceAsStream(tmp);
		Properties prop = new Properties();
		prop.load(in);
		String strfooter[] = prop.getProperty("footer").split(",");

	%>