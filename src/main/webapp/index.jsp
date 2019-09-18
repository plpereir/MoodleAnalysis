<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
<meta charset="utf-8">
<script src="jQuery/jquery-1.11.0.min.js"></script>
<script src="bootstrap-4.3.1-dist/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="bootstrap-4.3.1-dist/css/bootstrap.min.css">
<title>New Title</title>
</head>

<body>
<div class="container">
<h2>Hello World!</h2>

<div class="alert alert-success">
Alert Success
</div>
<form name="loginform" method="post" action="AnalysisActiveModules">
	<input type="submit" name="Submit" value="Submit">
</form>

<h1>${message}</h1>
</div>
</body>
</html>
