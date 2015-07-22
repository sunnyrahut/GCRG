restaurant.controller('DoneOrder', [ '$scope', '$location',
		function($scope, $location) {
			$scope.signOut = function() {
				$location.path('/loginOwner');
			};
		} ]);