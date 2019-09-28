<%@include file="library.jsp"%>

<%@ page isELIgnored="false"%>
<%@ page language="java" import="java.util.Properties"%>
<%@ page language="java" import="java.io.InputStream"%>
<%@include file="properties.jsp"%>

<body>
	<!-- Start Menu Bar-->
	<%@include file="header.jsp"%>
	<!--End Menu Bar-->

	<!-- Page Content -->
	<main role="main" class="container" >
	<div class="jumbotron" style="background-color: #ffffff;" id="idTitle">
		<h5 class="mt-4">Moodle Analytics</h5>
		<div ">
			<%out.println(prop.getProperty("homeTitle"));%>
			<br>
			<br>
		</div>

		<div class="container">
			<div class="accordion" id="accordionExample">
				<div class="card">
					<div class="card-header" id="headingOne">
						<h2 class="mb-0">
							<button class="btn btn-link" type="button" data-toggle="collapse"
								data-target="#collapseOne" aria-expanded="false"
								aria-controls="collapseOne">
								<%out.println(prop.getProperty("homeDashboard"));%>
							</button>
						</h2>
					</div>

					<div id="collapseOne" class="collapse" aria-labelledby="headingOne"
						data-parent="#accordionExample">
						<div class="card-body">
							<div id="hidden-div">
								<p><%out.println(prop.getProperty("homeDashobardDetails"));%></p>
								<br>
								<form name="loginform" method="post"
									action="AnalysisActiveModules">
									<input class="btn btn-primary" role="button" type="submit"
										name="Submit"
										value="<%out.println(prop.getProperty("homeDashboardButton"));%>"
										onclick="getElementById('hidden-div').style.display = 'none';getElementById('loadDiv').style.display = 'block';">
								</form>
							</div>
							<div id="loadDiv" style="display: none;" class="text-center">
								<img style="max-width: 30%"
									src="<%out.println(prop.getProperty("urlLoading"));%>" />
							</div>

						</div>
					</div>
				</div>
				<div class="card">
					<div class="card-header" id="headingTwo">
						<h2 class="mb-0">
							<button class="btn btn-link collapsed" type="button"
								data-toggle="collapse" data-target="#collapseTwo"
								aria-expanded="false" aria-controls="collapseTwo">
								<%out.println(prop.getProperty("homeDataMining"));%>
							</button>
						</h2>
					</div>
					<div id="collapseTwo" class="collapse" aria-labelledby="headingTwo"
						data-parent="#accordionExample">
						<div class="card-body">
							<div id="hidden-div2">
								<p><%out.println(prop.getProperty("homeDataMiningDetails"));%></p>
								<%String strArray[] = prop.getProperty("homeDataMiningModules").split(",");%>
								<form name="loginform2" method="post" action="DataMining">
									<p>
									<div class="form-check form-check-inline">
										<input class="form-check-input" type="radio" name="modules"
											id="inlineRadio1" value="mdl_analytics_models,analytics"> <label
											class="form-check-label" for="inlineRadio1"><%out.println(strArray[0]);%></label>
									</div>

									<div class="form-check form-check-inline">
										<input class="form-check-input" checked="true" type="radio"
											name="modules" id="inlineRadio2" value="mdl_assign,assign">
										<label class="form-check-label" for="inlineRadio2"><%out.println(strArray[1]);%></label>
									</div>

									<div class="form-check form-check-inline">
										<input class="form-check-input" type="radio" name="modules"
											id="inlineRadio3" value="mdl_granding_definitions,advanced_grading"> <label
											class="form-check-label" for="inlineRadio3"><%out.println(strArray[2]);%></label>
									</div>


									<div class="form-check form-check-inline">
										<input class="form-check-input" type="radio" name="modules"
											id="inlineRadio4" value="mdl_badge,badge"> <label
											class="form-check-label" for="inlineRadio4"><%out.println(strArray[3]);%></label>
									</div>

									<div class="form-check form-check-inline">
										<input class="form-check-input" type="radio" name="modules"
											id="inlineRadio5" value="mdl_course,course"> <label
											class="form-check-label" for="inlineRadio5"><%out.println(strArray[4]);%></label>
									</div>

									<div class="form-check form-check-inline">
										<input class="form-check-input" type="radio" name="modules"
											id="inlineRadio6" value="mdl_competency,competency"> <label
											class="form-check-label" for="inlineRadio6"><%out.println(strArray[5]);%></label>
									</div>

									<div class="form-check form-check-inline">
										<input class="form-check-input" type="radio" name="modules"
											id="inlineRadio7" value="mdl_forum,forum"> <label
											class="form-check-label" for="inlineRadio7"><%out.println(strArray[6]);%></label>
									</div>

									<div class="form-check form-check-inline">
										<input class="form-check-input" type="radio" name="modules"
											id="inlineRadio8" value="mdl_grade_items,grading"> <label
											class="form-check-label" for="inlineRadio8"><%out.println(strArray[7]);%></label>
									</div>

									<div class="form-check form-check-inline">
										<input class="form-check-input" type="radio" name="modules"
											id="inlineRadio9" value="mdl_lesson,lesson"> <label
											class="form-check-label" for="inlineRadio9"><%out.println(strArray[8]);%></label>
									</div>

									<div class="form-check form-check-inline">
										<input class="form-check-input" type="radio" name="modules"
											id="inlineRadio10" value="mdl_user,users_and_profiles"> <label
											class="form-check-label" for="inlineRadio10"><%out.println(strArray[9]);%></label>
									</div>

									<div class="form-check form-check-inline">
										<input class="form-check-input" type="radio" name="modules"
											id="inlineRadio11" value="mdl_question,question"> <label
											class="form-check-label" for="inlineRadio11"><%out.println(strArray[10]);%></label>
									</div>

									<div class="form-check form-check-inline">
										<input class="form-check-input" type="radio" name="modules"
											id="inlineRadio12" value="mdl_role,roles"> <label
											class="form-check-label" for="inlineRadio12"><%out.println(strArray[11]);%></label>
									</div>

									<div class="form-check form-check-inline">
										<input class="form-check-input" type="radio" name="modules"
											id="inlineRadio13" value="mdl_quiz,quiz"> <label
											class="form-check-label" for="inlineRadio13"><%out.println(strArray[12]);%></label>
									</div>

									<div class="form-check form-check-inline">
										<input class="form-check-input" type="radio" name="modules"
											id="inlineRadio14" value="mdl_scorm,scorm"> <label
											class="form-check-label" for="inlineRadio14"><%out.println(strArray[13]);%></label>
									</div>

									<div class="form-check form-check-inline">
										<input class="form-check-input" type="radio" name="modules"
											id="inlineRadio15" value="mdl_survey,survey"> <label
											class="form-check-label" for="inlineRadio15"><%out.println(strArray[14]);%></label>
									</div>

									<div class="form-check form-check-inline">
										<input class="form-check-input" type="radio" name="modules"
											id="inlineRadio16" value="mdl_wiki|wiki"> <label
											class="form-check-label" for="inlineRadio16"><%out.println(strArray[15]);%></label>
									</div>

									<div class="form-check form-check-inline">
										<input class="form-check-input" type="radio" name="modules"
											id="inlineRadio17" value="mdl_workshop,workshop"> <label
											class="form-check-label" for="inlineRadio17"><%out.println(strArray[16]);%></label>
									</div>
									<br><br>
									<input class="btn btn-primary" role="button" type="submit"
										name="Submit2"
										value="<%out.println(prop.getProperty("homeDataMiningButton"));%>"
										onclick="getElementById('hidden-div2').style.display = 'none';getElementById('loadDiv2').style.display = 'block';">
								</form>
							</div>
							<div id="loadDiv2" style="display: none;" class="text-center">
								<img style="max-width: 30%"
									src="<%out.println(prop.getProperty("urlLoading"));%>" />
							</div>

						</div>
					</div>
				</div>
			</div>
		</div>
	<!-- /.container --> <!-- footer start --> 
	<%@include file="footer.jsp"%>
	<!-- footer end --> </main>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
	<script>
		window.jQuery
				|| document
						.write('<script src="https://getbootstrap.com/docs/4.3/assets/js/vendor/jquery-slim.min.js"><\/script>')
	</script>
	<script
		src="https://getbootstrap.com/docs/4.3/dist/js/bootstrap.bundle.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/docsearch.js@2/dist/cdn/docsearch.min.js"></script>
	<script src="https://getbootstrap.com/docs/4.3/assets/js/docs.min.js"></script>

</body>
</html>
