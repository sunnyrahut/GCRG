var gcrg = angular.module('gcrg', [ 'ngRoute', 'chart.js' ]);

gcrg.run([ '$http', '$templateCache', function($http, $templateCache) {
	$http.get('addUser.html', {
		cache : $templateCache
	});
	$http.get('loginExistingUser.html', {
		cache : $templateCache
	});
	$http.get('contacts.html', {
		cache : $templateCache
	});
	$http.get('userDetails.html', {
		cache : $templateCache
	});
	$http.get('doneUser.html', {
		cache : $templateCache
	});
	$http.get('editUser.html', {
		cache : $templateCache
	});
	$http.get('viewUsers.html', {
		cache : $templateCache
	});
	$http.get('line-customTooltips.html', {
		cache : $templateCache
	});
	$http.get('fileUpload.html', {
		cache : $templateCache
	});
} ]);

gcrg.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/addUser', {
		templateUrl : 'addUser.html',
		controller : 'AddUser'
	}).when('/doneUser', {
		templateUrl : 'doneUser.html',
		controller : 'DoneUser'
	}).when('/loginExistingUser', {
		templateUrl : 'loginExistingUser.html',
		controller : 'LoginExistingUser',
		resolve : {
			users : [ '$http', function($http) {
				return $http({
					method : 'GET',
					url : 'rest/users/getAll'
				});
			} ]
		}
	}).when('/viewUsers', {
		templateUrl : 'viewUsers.html',
		controller : 'ViewUsers',
		resolve : {
			users : [ '$http', function($http) {
				return $http({
					method : 'GET',
					url : 'rest/contacts/getAll'
				});
			} ]
		}
	}).when('/userDetails/:id', {
		templateUrl : 'userDetails.html',
		controller : 'UserDetails',
	}).when('/contacts', {
		templateUrl : 'contacts.html',
		controller : 'Contact',
		resolve : {
			contacts : [ '$http', function($http) {
				return $http({
					method : 'GET',
					url : 'rest/contacts/getAll'
				});
			} ]
		}
	}).when('/graph', {
		templateUrl : 'line-customTooltips.html',
		controller : 'DisplayGraph',
		resolve : {
			dataValues : [ '$http', function($http) {
				return $http({
					method : 'GET',
					url : 'rest/atq/getAll'
				});
			} ]
		}
	}).when('/fileUpload', {
		templateUrl : 'fileUpload.html',
		controller : 'FileUpload'
	});
} ]);