restaurant.controller('Contact', [ '$scope', '$location', 'people',
		function($scope, $location, people) {
			$scope.people = people.data.data;
			$scope.signOut = function() {
				$location.path('/loginOwner');
			};
		} ]);