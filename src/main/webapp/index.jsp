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
								aria-controls="collapseOne">Module Usage Diagnostics</button>
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
								Mining Reports</button>
						</h2>
					</div>
					<div id="collapseTwo" class="collapse" aria-labelledby="headingTwo"
						data-parent="#accordionExample">
						<div class="card-body">
							<p>For each module two data mining reports: association
								(Apriori) and clustering (EM).</p>
							<p>Select the module and report type.</p>

							<div class="container">
								<div class="accordion" id="accordionDM">
									<div class="card">
										<div class="card-header" id="headingDMOne">
											<h2 class="mb-0">
												<button class="btn btn-link" type="button"
													data-toggle="collapse" data-target="#collapseDMOne"
													aria-expanded="false" aria-controls="collapseDMOne">
													Analytics</button>
											</h2>
										</div>

										<div id="collapseDMOne" class="collapse"
											aria-labelledby="headingDMOne" data-parent="#accordionDM">
											<div class="card-body">
																			<p>Click the button below to start the analysis on the use
									of the modules.</p>
								<form name="loginform2" method="post" action="DataMining">
									<input class="btn btn-primary" role="button" type="submit"
										name="Submit2" value="Load dashboard"
										onclick="getElementById('hidden-div').style.display = 'none';getElementById('loadDiv').style.display = 'block';">
								</form>
												<button type="button" class="btn btn-primary"
													data-toggle="modal" data-target="#DataMiningModal">Association
													(Apriori)</button>
												<button type="button" class="btn btn-primary">Clustering
													(EM)</button>
											</div>
										</div>
									</div>

									<div class="card">
										<div class="card-header" id="headingDMTwo">
											<h2 class="mb-0">
												<button class="btn btn-link collapsed" type="button"
													data-toggle="collapse" data-target="#collapseDMTwo"
													aria-expanded="false" aria-controls="collapseDMTwo">
													Data Mining Reports</button>
											</h2>
										</div>
										<div id="collapseDMTwo" class="collapse"
											aria-labelledby="headingDMTwo" data-parent="#accordionDM">
											<div class="card-body">For each module two data mining
												reports: association (Apriori) and clustering (EM).</div>
										</div>
									</div>
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

		<!-- Modal -->
		<div class="modal fade" style="max-width: 95%" id="DataMiningModal"
			tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
			aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="DataMiningModal">Data Mining -
							Analytics - Association (Apriori)</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<p>conteudo do relatorios</p>
						<p>Moodle Analytics Moodle analytics helps Moodle educators,
							education managers, and administrators make a diagnosis of how
							much modules are being used, as well as providing data mining
							reports. For unused modules, consultation of the main function of
							the module is available in order to encourage its use.</p>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
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
