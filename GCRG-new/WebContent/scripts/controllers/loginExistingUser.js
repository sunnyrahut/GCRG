gcrg.controller('LoginExistingUser', [ '$scope', '$location', '$q', '$http',
		'authenticateUser', '$window',
		function($scope, $location, $q, $http, authenticateUser, $window) {
			$scope.login = function() {
				var obj = {
					"firstName" : "",
					"lastName" : "",
					"email" : $scope.email,
					"phone" : "",
					"password" : $scope.password,
					"timeStamp" : new Date(),
					"userType" : ""
				};
				authenticateUser.login(obj);
			}
		} ]);