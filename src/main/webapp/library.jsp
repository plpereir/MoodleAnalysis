<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
</head>
<meta charset="utf-8">
<title>Moodle - Analytics</title>
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
.modal-dialog,
.modal-content {
    /* 80% of window height */
    height: 80%;
}

.modal-body {
    /* 100% = dialog height, 120px = header + footer */
    max-height: calc(100% - 120px);
    overflow-y: scroll;
}
@media (max-width: 800px) {
  #idTitle {
   margin-top: 20%;
  }
}
</style>

<!-- Favicons -->
<link rel="icon"
	href="https://moodle.org/theme/image.php/moodleorg/theme_moodleorg/1568888888/favicons/org/favicon-57">
