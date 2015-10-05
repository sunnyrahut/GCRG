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
										"timeStamp" : new Date(),
										"userType" : "User"
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