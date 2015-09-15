<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html ng-app="gcrg">
<head>

<!--[if lt IE 9]>
      <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
      <![endif]-->
<meta http-equiv="x-ua-compatible" content="IE=Edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>GCRG</title>
<link rel="stylesheet" type="text/css"
	href="scripts/lib/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="sample.css">
<link rel="stylesheet" type="text/css"
	href="scripts/lib/angular-chart.js/angular-chart.css">
<link rel="stylesheet" type="text/css"
	href="scripts/lib/bootstrap/css/bootstrap-theme.min.css">
<link rel="stylesheet" type="text/css"
	href="font-awesome-4.1.0/css/font-awesome.css">
<link rel="stylesheet" type="text/css"
	href="font-awesome-4.1.0/css/font-awesome.min.css">
<link href='http://fonts.googleapis.com/css?family=Lobster+Two'
	rel='stylesheet' type='text/css'>
</head>
<body>
	<div ng-view></div>
	<footer>
		<p align="center">
			<b>This material is based upon work supported by the National
				Science Foundation under NSF OPP: 9732105 and 0119060 Any opinions,
				findings, conclusions, or recommendations expressed in the material
				are those of the author(s) and do not necessarily reflect the views
				of the National Science Foundation.</b>
		</p>
	</footer>
	<!-- JavaScripts libraries-->
	<script type="text/javascript"
		src="http://maps.googleapis.com/maps/api/js"></script>
	<script type="text/javascript"
		src="scripts/lib/bootstrap/js/jquery-1.11.0.min.js">
		
	</script>
	<script type="text/javascript"
		src="scripts/lib/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript"
		src="scripts/lib/angular-1.4.3/angular.min.js"></script>
	<script type="text/javascript"
		src="scripts/lib/angular-1.4.3/angular-resource.min.js"></script>
	<script type="text/javascript"
		src="scripts/lib/angular-1.4.3/angular-animate.min.js"></script>
	<script type="text/javascript"
		src="scripts/lib/angular-1.4.3/angular-route.min.js"></script>
	<script type="text/javascript"
		src="scripts/lib/Chart.js-master/Chart.js"></script>
	<script type="text/javascript"
		src="scripts/lib/angular-chart.js/angular-chart.js"></script>
	<!-- 	<script type="text/javascript" src="scripts/script.js"></script> -->
	<script type="text/javascript" src="scripts/app.js"></script>
	<script type="text/javascript" src="scripts/controllers/addUser.js"></script>
	<script type="text/javascript"
		src="scripts/controllers/loginExistingUser.js"></script>
	<script type="text/javascript" src="scripts/controllers/doneUser.js"></script>
	<script type="text/javascript" src="scripts/controllers/updateUser.js"></script>
	<script type="text/javascript" src="scripts/controllers/userDetails.js"></script>
	<script type="text/javascript" src="scripts/controllers/addUser.js"></script>
	<script type="text/javascript" src="scripts/controllers/editUser.js"></script>
	<script type="text/javascript" src="scripts/controllers/doneUser.js"></script>
	<script type="text/javascript" src="scripts/controllers/viewUsers.js"></script>
	<script type="text/javascript" src="scripts/controllers/contacts.js"></script>
	<script type="text/javascript" src="scripts/controllers/fileUpload.js"></script>
	<script type="text/javascript"
		src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js">
		
	</script>
	<script type="text/javascript"
		src="scripts/controllers/displayGraph.js"></script>
</body>

</html>