gcrg.controller('DoneUser', [ '$scope', '$location',
		function($scope, $location) {
			$scope.signOut = function() {
				$location.path('/loginOwner');
			};
		} ]);