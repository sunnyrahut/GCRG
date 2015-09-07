gcrg.controller('Contact', [ '$scope', '$location', 'contacts',
		function($scope, $location, contacts) {
			$scope.contacts = contacts.data.data;
			$scope.signOut = function() {
				$location.path('/addUser');
			};
		} ]);