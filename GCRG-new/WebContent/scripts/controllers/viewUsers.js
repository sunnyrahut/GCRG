gcrg.controller('ViewUsers', [ '$scope', '$location', '$window', '$http',
		'users', 'authenticateUser',
		function($scope, $location, $window, $http, users, authenticateUser) {
			$scope.people = users.data.data;
			$scope.editDetails = function(id) {
				$location.path('/userDetails/' + id);
			}
			$scope.deleteUser = function(id) {
				$http({
					method : 'POST',
					url : 'rest/users/delete/' + id,
					responseType : 'json'
				}).success(function(data, status, headers, config) {
					console.log(data, status, headers, config);
					$location.path('/viewUsers');
				}).error(function(data, status, headers, config) {
					console.log(data, status, headers, config);
					alert("Unable to delete the user");
					$location.path('/viewUsers');
				});
			};
			$scope.signOut = function() {
				$window.sessionStorage["userInfo"] = null;
				userInfo = null;
				$location.path('/loginExistingUser');
			};
		} ]);