restaurant.controller('EditProfile', [ '$scope', '$location', '$http',
		'$routeParams', function($scope, $location, $http, $routeParams) {

			$http({
				method : 'GET',
				url : 'rest/owner/get/' + $routeParams.id,
				responseType : 'json'
			}).success(function(data, status, headers, config) {
				console.log(data, status, headers, config);
				$scope.roleId = data.data.roleId;
				$scope.role = data.data.role;
				$scope.name = data.data.firstName;
				$scope.phone = data.data.phone;
				$scope.email = data.data.email;
			}).error(function(data, status, headers, config) {
				console.log(data, status, headers, config);
			});

			$scope.updateProfile = function() {
				var obj = {
					"roleId" : $scope.roleId,
					"role" : $scope.role,
					"firstName" : $scope.email,
					"phone" : $scope.phone,
					"email" : $scope.email,
				};
				$http({
					method : 'POST',
					url : 'rest/owner/update',
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