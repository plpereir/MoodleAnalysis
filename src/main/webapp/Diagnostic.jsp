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
			<canvas id="canvas"></canvas>
			<script>
              var MONTHS = ['analytics','assignment','advanced grading','badge','course','competency','forum','grading','lesson','messages','question bank','question types','roles','quiz','scorm','survey','users and profiles','wiki','workshop'];
              var color = Chart.helpers.color;
              var horizontalBarChartData = {
                labels: ['analytics','assignment','advanced grading','badge','course','competency','forum','grading','lesson','messages','question bank','question types','roles','quiz','scorm','survey','users and profiles','wiki','workshop'],
                datasets: [ {
                  label: 'module tables used',
                  backgroundColor: color(window.chartColors.blue).alpha(0.5).rgbString(),
                  borderColor: window.chartColors.blue,
                  data:${message1}
                }]
              };
              window.onload = function() {
                var ctx = document.getElementById('canvas').getContext('2d');
                window.myHorizontalBar = new Chart(ctx, {
                  type: 'horizontalBar',
                  data: horizontalBarChartData,
                  options: {
                    // Elements options apply to all of the options unless overridden in a dataset
                    // In this case, we are setting the border of each horizontal bar to be 2px wide
                    elements: {
                      rectangle: {
                        borderWidth: 2,
                      }
                    },
                    responsive: true,
                    legend: {
                      position: 'right',
                      display:false,
                    },
                    title: {
                      display: true,
                      text: 'Use Modules'
                    }
                  }
                });
              };
            </script>
			<div>${message2}</div>

		</div>
		<!-- /.container -->
		<!-- footer start -->
		<%@include file="footer.jsp"%>

		<!-- footer end -->
</body>
</html>