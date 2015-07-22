restaurant.controller('OwnerHome', [ '$scope', '$location', 'people',
		function($scope, $location, people) {
			$scope.signOut = function() {
				$location.path('/loginOwner');
			};
			$scope.people = people.data.data;
			$scope.customerDetails = function(id) {
				console.log('Executed');
				$location.path('/customerDetails/' + id);
			};
		} ]);
