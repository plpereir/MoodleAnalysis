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

		<div class="container"><p>Follow data mining reports from <b> ${module} </b> module.</p></div>

		<div class="container">

			<div class="row">
				<div class="col-md-6">
					<div class="card mb-6 box-shadow">
						<div class="card-body">
							<p class="card-text">Class implementing an <b>Apriori-type
								algorithm</b>.</p>
								<p> Iteratively reduces the minimum support until it
								finds the required number of rules with the given minimum
								confidence.</p>
							<p>The algorithm has an option to mine class association
								rules. It is adapted as explained in the second reference.</p>
							<p>For more information see:</p>
							<p>R. Agrawal, R. Srikant: Fast Algorithms for Mining
								Association Rules in Large Databases. In: 20th International
								Conference on Very Large Data Bases, 478-499, 1994.</p>
							<div class="d-flex justify-content-between align-items-center">
								<div class="btn-group">
								<a class="btn btn-sm btn-primary" href='AprioriDetails?module=${module}&pathfile=${pathfile}' target="_blank"><span class="glyphicon glyphicon-download-alt" aria-hidden="true"></span>Download Report</a>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="card mb-6 box-shadow">
						<div class="card-body">
							<p class="card-text"><b>Simple EM (expectation maximisation)</b>
								class.</p>

							<p class="card-text">EM assigns a probability distribution to
								each instance which indicates the probability of it belonging to
								each of the clusters. EM can decide how many clusters to create
								by cross validation, or you may specify apriori how many
								clusters to generate.</p>
							<p class="card-text">The cross validation performed to
								determine the number of clusters is done in the following steps:</p>
							<ul>
								<li>1. the number of clusters is set to 1</li>
								<li>2. the training set is split randomly into 10 folds.</li>
								<li>3. EM is performed 10 times using the 10 folds the
									usual CV way.</li>
								<li>4. the loglikelihood is averaged over all 10 results.</li>
								<li>5. if loglikelihood has increased the number of
									clusters is increased by 1 and the program continues at step 2.
								</li>
							</ul>
							<p class="card-text">The number of folds is fixed to 10, as
								long as the number of instances in the training set is not
								smaller 10. If this is the case the number of folds is set equal
								to the number of instances.</p>
							<div class="d-flex justify-content-between align-items-center">
								<div class="btn-group">
									<a class="btn btn-sm btn-primary" href='EMDetails?module=${module}&pathfile=${pathfile}' target="_blank"><span class="glyphicon glyphicon-download-alt" aria-hidden="true"></span>Download Report</a>
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