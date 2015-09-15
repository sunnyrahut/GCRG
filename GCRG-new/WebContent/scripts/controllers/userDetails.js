gcrg.controller('UserDetails', function($scope, $location, $http, $route,
		$routeParams, $window, auth) {
	$http({
		method : 'GET',
		url : 'rest/users/get/' + $routeParams.id,
		responseType : 'json'
	}).success(function(data, status, headers, config) {
		console.log(data, status, headers, config);
		$scope.person = data.data;
	}).error(function(data, status, headers, config) {
		console.log(data, status, headers, config);
	});

	$scope.updateUser = function(user) {
		var obj = {
			"userID" : user.userID,
			"firstName" : $scope.person.firstName,
			"lastName" : $scope.person.lastName,
			"email" : $scope.person.email,
			"phone" : $scope.person.phone,
			"password" : user.password,
			"timeStamp" : user.timeStamp
		};
		$http({
			method : 'POST',
			url : 'rest/users/update',
			responseType : 'json',
			data : obj
		}).success(function(data, status, headers, config) {
			console.log(data, status, headers, config);
			$location.path('/viewUsers');
		}).error(function(data, status, headers, config) {
			console.log(data, status, headers, config);
		});
	};

	$scope.signOut = function() {
		$window.sessionStorage["userInfo"] = null;
		userInfo = null;
		$location.path('/loginExistingUser');
	};

});