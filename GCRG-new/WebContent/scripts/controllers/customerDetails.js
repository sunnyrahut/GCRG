restaurant.controller('CustomerDetails', function($scope, $location, $http,
		$route, $routeParams) {
	$http({
		method : 'GET',
		url : 'rest/people/get/' + $routeParams.id,
		responseType : 'json'
	}).success(function(data, status, headers, config) {
		console.log(data, status, headers, config);
		$scope.person = data.data;
	}).error(function(data, status, headers, config) {
		console.log(data, status, headers, config);
	});

	$scope.assignTable = function() {
		var d = new Date();
		var obj = {
			"tableNo" : parseInt($scope.table),
			"bookingId" : $scope.person.id,
			"tableStatus" : "Occupied",
			"since" : d.getFullYear() + "-" + d.getMonth() + "-" + d.getDate()
					+ " " + d.getHours() + ":" + d.getMinutes() + ":"
					+ d.getSeconds()
		};
		$http({
			method : 'POST',
			url : 'rest/table/update',
			responseType : 'json',
			data : obj
		}).success(function(data, status, headers, config) {
			console.log(data, status, headers, config);
		}).error(function(data, status, headers, config) {
			console.log(data, status, headers, config);
		});

		$location.path('/seating');
	};

	$scope.deleteReservation = function() {
		console.log('working');
		$http({
			method : 'POST',
			url : 'rest/people/delete/' + $scope.person.id,
			responseType : 'json'
		}).success(function(data, status, headers, config) {
			console.log(data, status, headers, config);
		}).error(function(data, status, headers, config) {
			console.log(data, status, headers, config);
		});
		$location.path('/viewReservation');
	};

	$scope.signOut = function() {
		$location.path('/loginOwner');
	};

});