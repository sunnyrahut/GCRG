gcrg.controller('DisplayGraph', [
		'$scope',
		'$location',
		'$http',
		'auth',
		'$window',
		function($scope, $location, $http, auth, $window) {
			$scope.signOut = function() {
				$window.sessionStorage["userInfo"] = null;
				userInfo = null;
				$location.path('/loginExistingUser');
			};

			$scope.generateGraph = function() {
				var timeStamps = [];
				var parameter = [];
				var toDate = new Date($scope.toDate);
				toDate = toDate.getUTCFullYear() + '-'
						+ (toDate.getUTCMonth() + 1) + '-'
						+ toDate.getUTCDate() + ' ' + toDate.getHours() + ':'
						+ toDate.getMinutes();
				console.log(toDate);
				var fromDate = new Date($scope.fromDate);
				fromDate = fromDate.getUTCFullYear() + '-'
						+ (fromDate.getUTCMonth() + 1) + '-'
						+ fromDate.getUTCDate() + ' ' + fromDate.getHours()
						+ ':' + fromDate.getMinutes();
				switch ($scope.dataType) {
				case "atq_no_gap_filled":
					$http(
							{
								method : 'GET',
								url : 'rest/atq/getAllNoGap/' + fromDate + '/'
										+ toDate,
								responseType : 'json'
							}).success(function(data, status, headers, config) {
						console.log(data, status, headers, config);
						for (var i = 0; i < data.data.length; i++) {
							timeStamps.push(data.data[i].timeStamp);
							var par = $scope.parameter;
							parameter.push(data.data[i][par]);
							$scope.labels = timeStamps;
							$scope.series = [ $scope.parameter ];
							$scope.data = [ parameter ];
						}
					}).error(function(data, status, headers, config) {
						console.log(data, status, headers, config);
					});
					break;
				case "atq_gap_filled":
					$http({
						method : 'POST',
						url : 'rest/atq/getAllGap/' + fromDate + '/' + toDate,
						responseType : 'json'
					}).success(function(data, status, headers, config) {
						console.log(data, status, headers, config);
						for (var i = 0; i < data.data.length; i++) {
							timeStamps.push(data.data[i].timeStamp);
							var par = $scope.parameter;
							parameter.push(data.data[i][par]);
							$scope.labels = timeStamps;
							$scope.series = [ $scope.parameter ];
							$scope.data = [ parameter ];
						}
					}).error(function(data, status, headers, config) {
						console.log(data, status, headers, config);
					});
					break;
				case "atq_meteorological":
					$http(
							{
								method : 'POST',
								url : 'rest/atq/getAllMeteorological/'
										+ fromDate + '/' + toDate,
								responseType : 'json'
							}).success(function(data, status, headers, config) {
						console.log(data, status, headers, config);
						for (var i = 0; i < data.data.length; i++) {
							timeStamps.push(data.data[i].timeStamp);
							var par = $scope.parameter;
							parameter.push(data.data[i][par]);
							$scope.labels = timeStamps;
							$scope.series = [ $scope.parameter ];
							$scope.data = [ parameter ];
						}
					}).error(function(data, status, headers, config) {
						console.log(data, status, headers, config);
					});
					break;
				}
			};

			$scope.generateExcel = function() {

				var obj = {
					dataType : $scope.dataType,
					parameter : $scope.parameter
				}
				$http({
					method : 'POST',
					url : 'rest/atq/generateCsv',
					data : obj,
					responseType : 'json'
				}).success(function(data, status, headers, config) {
					console.log(data, status, headers, config);
					alert("CSV File Successfully Generated!!");
				}).error(function(data, status, headers, config) {
					console.log(data, status, headers, config);
				});
			};
		} ]);
