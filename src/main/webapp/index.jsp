<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8">
<script src="jQuery/jquery-1.11.0.min.js"></script>
<script src="bootstrap-4.3.1-dist/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="bootstrap-4.3.1-dist/css/bootstrap.min.css">

<!-- chart.js -->
<script src="https://www.chartjs.org/dist/2.8.0/Chart.min.js"></script>
<script src="https://www.chartjs.org/samples/latest/utils.js"></script>
<style>
canvas {
	-moz-user-select: none;
	-webkit-user-select: none;
	-ms-user-select: none;
}
</style>

<title>Moodle Analytics</title>
</head>

<body class="text-left">
	<div class="container">
		<div class="text-right">
			<img
				src="https://moodle.org/theme/image.php/moodleorg/theme_moodleorg/1568627409/moodle_logo_small"
				alt="Moodle" width="204px" height="61px">
			<p>Analytics</p>
		</div>
		<hr>

		<div class="alert alert-primary" role="alert">
			<p>Click the button below to showing chart with informations
				about use module this moodle instance.</p>
			<p>Case something moodle don't has data, showing informations
				about the moodle to incentive use.</p>
		</div>

		<form name="loginform" method="post" action="AnalysisActiveModules"
			id="hidden-div">
			<input class="btn btn-primary" role="button" type="submit"
				name="Submit" value="Load dashboard"
				onclick="getElementById('hidden-div').style.display = 'none';getElementById('loadDiv').style.display = 'block';">
		</form>

		<div id="loadDiv" style="display: none;" class="text-center">
			<img style="max-width: 30%"
				src="https://blog.teamtreehouse.com/wp-content/uploads/2015/05/InternetSlowdown_Day.gif" />
		</div>

		<div id="container">
			<canvas id="canvas"></canvas>
		</div>
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
		<div>
			<hr>

			${message2}
		</div>
	</div>
</body>
</html>
