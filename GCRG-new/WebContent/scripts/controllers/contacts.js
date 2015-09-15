gcrg.controller('Contact', [ '$scope', '$location', '$http', '$window',
		'$route', 'contacts', 'auth',
		function($scope, $location, $http, $window, $route, contacts, auth) {
			$scope.contacts = contacts.data.data;
			$scope.signOut = function() {
				$window.sessionStorage["userInfo"] = null;
				userInfo = null;
				$location.path('/loginExistingUser');
			};
			$scope.addContact = function() {
				var obj = {
					"name" : $scope.name,
					"role" : $scope.role,
					"email" : $scope.email,
					"phone" : $scope.phone
				};
				$http({
					method : 'POST',
					url : 'rest/contacts/add',
					data : obj,
					responseType : 'json'
				}).success(function(data, status, headers, config) {
					console.log(data, status, headers, config);
					$route.reload();
				}).error(function(data, status, headers, config) {
					console.log(data, status, headers, config);
				});
			};
		} ]);