<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html ng-app="restaurant">
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

	<!-- JavaScripts libraries-->
	<script type="text/javascript"
		src="scripts/lib/bootstrap/js/jquery-1.11.0.min.js"></script>
	<script type="text/javascript"
		src="scripts/lib/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="scripts/lib/angular.min.js"></script>
	<script type="text/javascript"
		src="scripts/lib/angular-resource.min.js"></script>
	<script type="text/javascript" src="scripts/lib/angular-animate.min.js"></script>
	<script type="text/javascript" src="scripts/lib/angular-route.min.js"></script>
	<!-- 	<script type="text/javascript" src="scripts/script.js"></script> -->
	<script type="text/javascript" src="scripts/app.js"></script>
	<script type="text/javascript" src="scripts/controllers/addOrder.js"></script>
	<script type="text/javascript" src="scripts/controllers/loginOwner.js"></script>
	<script type="text/javascript" src="scripts/controllers/doneOrder.js"></script>
	<script type="text/javascript" src="scripts/controllers/updateOrder.js"></script>
	<script type="text/javascript" src="scripts/controllers/ownerHome.js"></script>
	<script type="text/javascript" src="scripts/controllers/seating.js"></script>
	<script type="text/javascript"
		src="scripts/controllers/customerDetails.js"></script>
	<script type="text/javascript"
		src="scripts/controllers/addReservation.js"></script>
	<script type="text/javascript"
		src="scripts/controllers/editReservation.js"></script>
	<script type="text/javascript"
		src="scripts/controllers/doneReservation.js"></script>
	<script type="text/javascript" src="scripts/controllers/profile.js"></script>
	<script type="text/javascript" src="scripts/controllers/editProfile.js"></script>
	<script type="text/javascript" src="scripts/controllers/addProfile.js"></script>
	<script type="text/javascript" src="scripts/controllers/contacts.js"></script>

</body>

</html>

