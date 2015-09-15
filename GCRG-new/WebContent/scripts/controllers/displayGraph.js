gcrg.controller('DisplayGraph', [
		'$scope',
		'$location',
		'dataValues',
		'auth',
		'$window',
		function($scope, $location, dataValues, auth, $window) {
			$scope.signOut = function() {
				$window.sessionStorage["userInfo"] = null;
				userInfo = null;
				$location.path('/loginExistingUser');
			};

			$scope.generateGraph = function() {
				var timeStamps = [];
				var parameter = [];
				var toDate = new Date($scope.toDate);
				var fromDate = new Date($scope.fromDate);
				$location.path('/graph')
				for (var i = 0; i < dataValues.data.data.length; i++) {
					if ((fromDate <= (new Date(
							dataValues.data.data[i].timeStamp)))
							&& (toDate >= (new Date(
									dataValues.data.data[i].timeStamp)))) {
						timeStamps.push(dataValues.data.data[i].timeStamp);
						var par = $scope.parameter;
						console.log(dataValues.data.data[i][par]);
						parameter.push(dataValues.data.data[i][par]);
					}
				}
				console.log(dataValues.data.data[0].windSpeed);
				$scope.labels = timeStamps;
				$scope.series = [ $scope.parameter ];
				$scope.data = [ parameter ];
			};
		} ]);
