restaurant.controller('UpdateOrder', function($scope, $http, $location,
		$routeParams) {
	$scope.firstName = $routeParams.firstName;
	$scope.lastName = $routeParams.lastName;
	$scope.emailId = $routeParams.emailId;
	$scope.date = $routeParams.date;
	$scope.time = $routeParams.time;
	$scope.phone = $routeParams.phone;
	$scope.partySize = $routeParams.partySize;
	$scope.occasion = $routeParams.occasion;

	$scope.updateCustomer = function() {
		var obj = {
			"id" : $routeParams.id,
			"firstName" : $scope.firstName,
			"lastName" : $scope.lastName,
			"email" : $scope.emailId,
			"bookingDate" : $scope.date,
			"bookingTime" : $scope.time,
			"phone" : $scope.phone,
			"partySize" : $scope.partySize,
			"occasion" : $scope.occasion
		};
		$http({
			method : 'POST',
			url : 'rest/people/update',
			data : obj,
			responseType : 'json'
		}).success(function(data, status, headers, config) {
			console.log(data, status, headers, config);
		}).error(function(data, status, headers, config) {
			console.log(data, status, headers, config);
		});

		$location.path('/doneOrder');
	};
});