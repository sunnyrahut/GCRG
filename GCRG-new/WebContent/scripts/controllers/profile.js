restaurant.controller('Profile', [ '$scope', '$location', '$http', '$route',
		'auto', 'people',
		function($scope, $location, $http, $route, auto, people) {
			$scope.people = people.data.data;
			$scope.auto_assign = auto.data.data.auto;
			$scope.editProfile = function(roleId) {
				$location.path('/editProfile/' + roleId);
			};
			$scope.addProfile = function() {
				$location.path('/addProfile');
			};

			$scope.deleteProfile = function(roleId) {
				$http({
					method : 'POST',
					url : 'rest/owner/delete/' + roleId,
					responseType : 'json'
				}).success(function(data, status, headers, config) {
					console.log(data, status, headers, config);
				}).error(function(data, status, headers, config) {
					console.log(data, status, headers, config);
				});
				$route.reload();
			};

			$scope.signOut = function() {
				$location.path('/loginOwner');
			};

			$scope.update = function() {

				var obj = {
					"auto" : $scope.auto_assign
				};

				console.log(obj);

				$http({
					method : 'POST',
					url : 'rest/auto/update',
					responseType : 'json',
					data : obj
				}).success(function(data, status, headers, config) {
					console.log(data, status, headers, config);
				}).error(function(data, status, headers, config) {
					console.log(data, status, headers, config);
				});
			};
		} ]);