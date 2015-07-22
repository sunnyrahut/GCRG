restaurant.controller('AddProfile', [ '$scope', '$location', '$http',
		'$routeParams', function($scope, $location, $http, $routeParams) {

			$scope.add = function() {
				var obj = {
					"roleId" : $scope.roleId,
					"role" : $scope.role,
					"firstName" : $scope.email,
					"phone" : $scope.phone,
					"email" : $scope.email,
				};
				$http({
					method : 'POST',
					url : 'rest/owner/add',
					data : obj,
					responseType : 'json'
				}).success(function(data, status, headers, config) {
					console.log(data, status, headers, config);
				}).error(function(data, status, headers, config) {
					console.log(data, status, headers, config);
				});

				$location.path('/profile');
			};

			$scope.signOut = function() {
				$location.path('/loginOwner');
			};

		} ]);