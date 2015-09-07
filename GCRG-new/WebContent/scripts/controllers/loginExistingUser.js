gcrg.controller('LoginExistingUser', [
		'$scope',
		'$location',
		'users',
		function($scope, $location, users) {
			$scope.login = function() {
				var found = false;
				for (var i = 0; i < users.data.data.length; i++) {
					if (users.data.data[i].email == $scope.email
							&& users.data.data[i].password == $scope.password) {
						console.log(users.data.data[i].email);
						$location.path('/viewUsers');
						found = true;
						break;
					}
				}
				if (!found) {
					alert('User not found in the system!!');
					$location.path('/loginExistingUser');
				}
			}
		} ]);