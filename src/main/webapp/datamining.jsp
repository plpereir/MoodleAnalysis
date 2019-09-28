<!-- page start -->
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
	<main role="main" class="container">
	<div class="jumbotron" style="background-color: #ffffff;">
		<h5 class="mt-4">Moodle Analytics</h5>
		<p><%out.println(prop.getProperty("homeTitle"));%></p>

		<div class="container"><p><%out.println(prop.getProperty("miningreports"));%><b> ${module} </b>.</p></div>

		<div class="container">

			<div class="row">
				<div class="col-md-6">
					<div class="card mb-6 box-shadow">
						<div class="card-body">
							<p class="card-text"><%out.println(prop.getProperty("apriori"));%></p>
							<div class="d-flex justify-content-between align-items-center">
								<div class="btn-group">
								<a class="btn btn-sm btn-primary" href='AprioriDetails?module=${table}&pathfile=${pathfile}' target="_blank"><span class="glyphicon glyphicon-download-alt" aria-hidden="true"></span>Download Report</a>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="card mb-6 box-shadow">
						<div class="card-body">
							<p class="card-text"><%out.println(prop.getProperty("EM"));%></p>
							<div class="d-flex justify-content-between align-items-center">
								<div class="btn-group">
									<a class="btn btn-sm btn-primary" href='EMDetails?module=${table}&pathfile=${pathfile}' target="_blank"><span class="glyphicon glyphicon-download-alt" aria-hidden="true"></span>Download Report</a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- /.container -->
		<%@include file="footer.jsp"%>
		<!-- footer start -->

		<!-- footer end -->
	</div>
	</main>
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