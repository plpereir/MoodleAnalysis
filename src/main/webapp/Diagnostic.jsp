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
		
		<div class="container">
			<canvas id="canvas"></canvas>
			<script>
              var MONTHS = [<%out.println(prop.getProperty("Modules"));%>];
              var color = Chart.helpers.color;
              var horizontalBarChartData = {
                labels: [<%out.println(prop.getProperty("Modules"));%>],
                datasets: [ {
                  label: <%out.println(prop.getProperty("label"));%>,
                  backgroundColor: color(window.chartColors.blue).alpha(0.5).rgbString(),
                  borderColor: window.chartColors.blue,
                  data: ${message1}
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
                      text: <%out.println(prop.getProperty("chartText"));%>
                    }
                  }
                });
              };
            </script>
			<div>${message2}</div>
		</div>
		</div>
		<!-- /.container -->
		<!-- footer start -->
		<%@include file="footer.jsp"%>
		<!-- footer end -->
</body>
</html>