<!-- page start -->
<%@include file="library.jsp"%>
<body>
	<!-- Start Menu Bar-->
	<%@include file="header.jsp"%>
	<!--End Menu Bar-->

	<!-- Page Content -->
	<main role="main" class="container">
	<div class="jumbotron" style="background-color: #ffffff;">
		<h5 class="mt-4">Moodle Analytics</h5>
		<p>Moodle analytics helps Moodle educators, education managers,
			and administrators make a diagnosis of how much modules are being
			used, as well as providing data mining reports. For unused modules,
			consultation of the main function of the module is available in order
			to encourage its use.</p>

		<div class="container">
			<div class="accordion" id="accordionExample">
				<div class="card">
					<div class="card-header" id="headingOne">
						<h2 class="mb-0">
							<button class="btn btn-link" type="button" data-toggle="collapse"
								data-target="#collapseOne" aria-expanded="false"
								aria-controls="collapseOne">Module Usage Diagnostics (click here, more details)</button>
						</h2>
					</div>

					<div id="collapseOne" class="collapse" aria-labelledby="headingOne"
						data-parent="#accordionExample">
						<div class="card-body">
							<div id="hidden-div">
								<p>Click the button below to start the analysis on the use
									of the modules.</p>
								<form name="loginform" method="post"
									action="AnalysisActiveModules">
									<input class="btn btn-primary" role="button" type="submit"
										name="Submit" value="Load dashboard"
										onclick="getElementById('hidden-div').style.display = 'none';getElementById('loadDiv').style.display = 'block';">
								</form>
							</div>
							<div id="loadDiv" style="display: none;" class="text-center">
								<img style="max-width: 30%"
									src="https://blog.teamtreehouse.com/wp-content/uploads/2015/05/InternetSlowdown_Day.gif" />
							</div>

						</div>
					</div>
				</div>
				<div class="card">
					<div class="card-header" id="headingTwo">
						<h2 class="mb-0">
							<button class="btn btn-link collapsed" type="button"
								data-toggle="collapse" data-target="#collapseTwo"
								aria-expanded="false" aria-controls="collapseTwo">Data
								Mining Reports (click here, more details)</button>
						</h2>
					</div>
					<div id="collapseTwo" class="collapse" aria-labelledby="headingTwo"
						data-parent="#accordionExample">
						<div class="card-body">
							<div id="hidden-div2">
							<p>Select the module, 
							after this click Load Reports button 
							to generate two data mining reports: 
							association (Apriori) 
							and clustering (EM).</p>
								<form name="loginform2" method="post" action="DataMining">
									<input class="btn btn-primary" role="button" type="submit"
										name="Submit2" value="Load Reports"
										onclick="getElementById('hidden-div2').style.display = 'none';getElementById('loadDiv2').style.display = 'block';">
								</form>
							</div>	
							<div id="loadDiv2" style="display: none;" class="text-center">
								<img style="max-width: 30%"
									src="https://blog.teamtreehouse.com/wp-content/uploads/2015/05/InternetSlowdown_Day.gif" />
							</div>
							
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- /.container -->
		<!-- footer start -->
		<%@include file="footer.jsp"%>
		<!-- footer end -->

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
