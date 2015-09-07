gcrg.controller('FileUpload', [
		'$scope',
		'$location',
		'$http',
		function($scope, $location, $http) {
			$scope.upload = function() {
				console.log($scope.fileLocation.replace(/\\/g, "-"));
				$http(
						{
							method : 'POST',
							url : 'rest/upload/'
									+ $scope.fileLocation.replace(/\\/g, "-"),
							responseType : 'json'
						}).success(function(data, status, headers, config) {
					console.log(data, status, headers, config);
				}).error(function(data, status, headers, config) {
					console.log(data, status, headers, config);
				});
			}
		} ]);