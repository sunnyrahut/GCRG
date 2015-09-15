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
} ]);

gcrg.factory("authenticateUser", function($http, $q, $window, $location) {
	var userInfo;

	function login(obj) {
		var deferred = $q.defer();
		$http.post("rest/login/authenticate", obj).then(function(result) {
			if (result.data.status === "SUCCESS") {
				userInfo = {
					status : result.data.status,
					userName : result.data.data[0].email
				};
				$window.sessionStorage["userInfo"] = JSON.stringify(userInfo);
				$location.path('/viewUsers');
				deferred.resolve(userInfo);
			} else {
				alert("User credentials not found in the database!!");
				deferred.reject(userInfo);
			}
		}, function(error) {
			deferred.reject(error);
		});
		return deferred.promise;
	}

	return {
		login : login
	};
});

gcrg.run([
		"$rootScope",
		"$location",
		function($rootScope, $location) {
			$rootScope.$on("$routeChangeSuccess", function(userInfo) {
				console.log(userInfo);
			});

			$rootScope.$on("$routeChangeError", function(event, current,
					previous, eventObj) {
				if (eventObj.authenticated === false) {
					$location.path("/loginExistingUser");
				}
			});
		} ]);

gcrg
		.config([
				'$routeProvider',
				function($routeProvider) {
					$routeProvider
							.when('/addUser', {
								templateUrl : 'addUser.html',
								controller : 'AddUser'
							})
							.when('/doneUser', {
								templateUrl : 'doneUser.html',
								controller : 'DoneUser'
							})
							.when('/loginExistingUser', {
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
							})
							.when(
									'/viewUsers',
									{
										templateUrl : 'viewUsers.html',
										controller : 'ViewUsers',
										resolve : {
											users : [ '$http', function($http) {
												return $http({
													method : 'GET',
													url : 'rest/users/getAll'
												});
											} ],
											auth : [
													"$q",
													"$window",
													"authenticateUser",
													function($q, $window,
															authenticateUser) {
														var userInfo = JSON
																.parse($window.sessionStorage["userInfo"]);
														if (userInfo) {
															return $q
																	.when(userInfo);
														} else {
															return $q
																	.reject({
																		authenticated : false
																	});
														}
													} ]
										}
									})
							.when(
									'/userDetails/:id',
									{
										templateUrl : 'userDetails.html',
										controller : 'UserDetails',
										resolve : {
											auth : [
													"$q",
													"$window",
													"authenticateUser",
													function($q, $window,
															authenticateUser) {
														var userInfo = JSON
																.parse($window.sessionStorage["userInfo"]);

														if (userInfo) {
															return $q
																	.when(userInfo);
														} else {
															return $q
																	.reject({
																		authenticated : false
																	});
														}
													} ]
										}
									})
							.when(
									'/contacts',
									{
										templateUrl : 'contacts.html',
										controller : 'Contact',
										resolve : {
											contacts : [
													'$http',
													function($http) {
														return $http({
															method : 'GET',
															url : 'rest/contacts/getAll'
														});
													} ],
											auth : [
													"$q",
													"$window",
													"authenticateUser",
													function($q, $window,
															authenticateUser) {
														var userInfo = JSON
																.parse($window.sessionStorage["userInfo"]);

														if (userInfo) {
															return $q
																	.when(userInfo);
														} else {
															return $q
																	.reject({
																		authenticated : false
																	});
														}
													} ]
										}
									})
							.when(
									'/graph',
									{
										templateUrl : 'line-customTooltips.html',
										controller : 'DisplayGraph',
										resolve : {
											dataValues : [
													'$http',
													function($http) {
														return $http({
															method : 'GET',
															url : 'rest/atq/getAll'
														});
													} ],
											auth : [
													"$q",
													"$window",
													"authenticateUser",
													function($q, $window,
															authenticateUser) {
														var userInfo = JSON
																.parse($window.sessionStorage["userInfo"]);

														if (userInfo) {
															return $q
																	.when(userInfo);
														} else {
															return $q
																	.reject({
																		authenticated : false
																	});
														}
													} ]
										}
									}).otherwise({
								redirectTo : '/loginExistingUser'
							});
				} ]);