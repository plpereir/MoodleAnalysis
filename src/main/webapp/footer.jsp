<%@ page isELIgnored="false"%>
<%@ page language="java" import="java.util.Properties"%>
<%@ page language="java" import="java.io.InputStream"%>

<%
//set cookies variables
Cookie cookief = null;
Cookie[] cookiesf = null;

/*
English = false;
Portuguese = true;
*/
Boolean setLangf = false;
// Get an array of Cookies associated with the this domain
cookiesf = request.getCookies();

if (request.getParameter("lg") != null) {
	if (request.getParameter("lg").compareTo("PT") == 0) {
		setLangf = true;
	}
	// Create cookies for first and last names.      
	Cookie newcookief = new Cookie("moodleAnalyticsLanguage", request.getParameter("lg"));

	// Set expiry date after 24 Hrs for both the cookies.
	newcookief.setMaxAge(60 * 60 * 24);

	// Add both the cookies in the response header.
	response.addCookie(newcookief);

} else {
	if (cookiesf != null) {
		for (int i = 0; i < cookiesf.length; i++) {
			cookief = cookiesf[i];

			if (cookief.getName().compareTo("moodleAnalyticsLanguage") == 0) {
				if (cookief.getValue().compareTo("PT") == 0) {
					setLangf = true;
				}
			}
		}
	}
}
//check get parameter
String tmpf = "";
if (setLangf) {
	//read file type config
	tmpf = "/WEB-INF/properties/PTlang.properties";
} else {
	tmpf = "/WEB-INF/properties/ENlang.properties";
}
//set properties, and load properties
InputStream inf = getServletContext().getResourceAsStream(tmpf);
Properties propf = new Properties();
propf.load(inf);
String strfooter[] = propf.getProperty("footer").split(",");
%>
<div class="container">
	<footer class="pt-4 my-md-5 pt-md-5 border-top">
		<div class="row">
			<div class="col-12 col-md">
				<h5><%out.println(strfooter[0]);%></h5>
				<ul class="list-unstyled text-small">
					<li><a class="text-muted" href="https://docs.moodle.org/" target="_blank"><%out.println(strfooter[1]);%></a></li>
					<li><a class="text-muted" href="https://moodle.org/news/" target="_blank"><%out.println(strfooter[2]);%></a></li>
					<li><a class="text-muted" href="https://moodle.org/community/" target="_blank"><%out.println(strfooter[3]);%></a></li>
				</ul>
			</div>
			<div class="col-6 col-md">
				<h5><%out.println(strfooter[4]);%></h5>
				<ul class="list-unstyled text-small">
					<li><a class="text-muted" href="https://docs.moodle.org/dev/" target="_blank"><%out.println(strfooter[5]);%></a></li>
					<li><a class="text-muted"
						href="https://docs.moodle.org/dev/Roadmap" target="_blank"><%out.println(strfooter[6]);%></a></li>
					<li><a class="text-muted"
						href="https://moodle.org/mod/forum/view.php?id=55" target="_blank"><%out.println(strfooter[7]);%></a></li>
				</ul>
			</div>
			<div class="col-6 col-md">
				<h5><%out.println(strfooter[8]);%></h5>
				<ul class="list-unstyled text-small">
					<li><a class="text-muted"
						href="https://github.com/plpereir/MoodleAnalysis" target="_blank"><%out.println(strfooter[9]);%></a></li>
					<li><a class="text-muted"
						href="https://www.linkedin.com/in/pedro-luiz-da-silva-pereira-4031a624/" target="_blank"><%out.println(strfooter[10]);%></a></li>
					<li><a class="text-muted"
						href="https://docs.google.com/presentation/d/1LEvcE73mZ1eREgpwTp_Gxp4eercY6WWo1YaTPcTpU8E" target="_blank"><%out.println(strfooter[11]);%></a></li>
				</ul>
			</div>
			<div class="col-6 col-md">
				<img class="mb-2"
					src="https://moodle.org/theme/image.php/moodleorg/theme_moodleorg/1568888888/moodle_logo_small"
					alt="" width="25%" height="25%"> <small
					class="d-block mb-3 text-muted"> 2019</small>
			</div>
		</div>
	</footer>
</div>