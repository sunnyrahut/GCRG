restaurant.controller('Seating', [ '$scope', '$location', 'tables',
		function($scope, $location, tables) {
			$scope.tables = tables.data.data;
			$scope.signOut = function() {
				$location.path('/loginOwner');
			};
		} ]);