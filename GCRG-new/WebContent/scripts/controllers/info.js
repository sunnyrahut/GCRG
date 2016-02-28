gcrg.controller('Info', [ '$scope', '$location', function($scope, $location) {
	$scope.signOut = function() {
		$location.path('/loginOwner');
	};
} ]);