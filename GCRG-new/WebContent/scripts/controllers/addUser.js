gcrg
		.controller(
				'AddUser',
				[
						'$scope',
						'$http',
						'$location',
						function($scope, $http, $location) {
							$scope.addUser = function() {
								if ($scope.password != $scope.re_password) {
									alert("password do not match");
								} else {
									var obj = {
										"firstName" : $scope.firstName,
										"lastName" : $scope.lastName,
										"email" : $scope.emailId,
										"phone" : $scope.phone,
										"password" : $scope.password,
										"timeStamp" : new Date()
									};
									console.log(obj);
									$http({
										method : 'POST',
										url : 'rest/users/add',
										data : obj,
										responseType : 'json'
									}).success(
											function(data, status, headers,
													config) {
												console.log(data, status,
														headers, config);
												$location.path('/doneUser');
											}).error(
											function(data, status, headers,
													config) {
												console.log(data, status,
														headers, config);
											});
								}
							};

							$scope.getUser = function() {
								var id = $scope.userId, firstName, lastName, emailId, phone, password;
								$http({
									method : 'GET',
									url : 'rest/users/get/' + id,
									responseType : 'json'
								})
										.success(
												function(data, status, headers,
														config) {
													console.log(data, status,
															headers, config);
												}).error(
												function(data, status, headers,
														config) {
													console.log(data, status,
															headers, config);
												});
							};

						} ]);

var obj = {
	name : "Sunny Rahut Jagadeesh",
	email : "sunnyrahut@gmail.com",
	phoneNumber : "619-356-2924",
	resume : "•	At Knowledge Made Solutions developed a security system using HTML5, CSS3, AngularJS, and JavaScript to trace the work flow of redacted images having secured data. Java was used on the server side to launch the applications based on the JSON input to the RESTful framework coming from the front end. At Egen Solutions, Inc worked in a team to  develop, update and execute an automation testing framework in JavaScript, NodeJS and CucumberJS for our client JPMorgan Chase. Developed a demo restaurant web application in AngularJS, JavaScript, JQuery, HTML5 and CSS3 for the front end and RESTful framework (Jersey and Jackson) in Java for the backend. At San Diego State University Research Foundation fixed and maintained the website for Global Change Research Group, the website was developed in PHP. At Quinnox worked on Hp’s Quick Test Professional using data-driven, keyword driven and hybrid frameworks to develop and execute test scripts on Waste Management, Inc’s websites and software. Reduced the time needed to execute automation test scripts by developing a master data setup for our client Waste Management, Inc. Developed automation test scripts using Hp’s Quick Test Professional in VB .Net for validating French words and sentences in an AS400 system, received client appreciation for this impeccable job. In a team of 5 worked on web a project to maintain orders for a computer hardware company, technologies used were HTML, CSS, JSPs and Servlets for the front end and Java for the backend with MySQL database"
}