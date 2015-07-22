var restaurant = angular.module('restaurant', [ 'ngRoute' ]);

restaurant.run([ '$http', '$templateCache', function($http, $templateCache) {
	$http.get('addOrder.html', {
		cache : $templateCache
	});
	$http.get('loginOwner.html', {
		cache : $templateCache
	});
	$http.get('addProfile.html', {
		cache : $templateCache
	});
	$http.get('addReservation.html', {
		cache : $templateCache
	});
	$http.get('contacts.html', {
		cache : $templateCache
	});
	$http.get('customerDetails.html', {
		cache : $templateCache
	});
	$http.get('customerStatus.html', {
		cache : $templateCache
	});
	$http.get('doneOrder.html', {
		cache : $templateCache
	});
	$http.get('doneReservation.html', {
		cache : $templateCache
	});
	$http.get('editProfile.html', {
		cache : $templateCache
	});
	$http.get('editReservation.html', {
		cache : $templateCache
	});
	$http.get('profile.html', {
		cache : $templateCache
	});
	$http.get('seating.html', {
		cache : $templateCache
	});
	$http.get('updateOrder.html', {
		cache : $templateCache
	});
	$http.get('viewReservation.html', {
		cache : $templateCache
	});
} ]);

restaurant
		.config([
				'$routeProvider',
				function($routeProvider) {
					$routeProvider
							.when('/addOrder', {
								templateUrl : 'addOrder.html',
								controller : 'AddOrder',
								resolve : {
									auto : [ '$http', function($http) {
										return $http({
											method : 'GET',
											url : 'rest/auto/get'
										});
									} ],
									table : [ '$http', function($http) {
										return $http({
											method : 'GET',
											url : 'rest/table/getAvailable'
										});
									} ]
								}
							})

							.when('/doneOrder', {
								templateUrl : 'doneOrder.html',
								controller : 'DoneOrder'
							})
							.when('/loginOwner', {
								templateUrl : 'loginOwner.html',
								controller : 'LoginOwner'
							})
							.when('/viewReservation', {
								templateUrl : 'viewReservation.html',
								controller : 'OwnerHome',
								resolve : {
									people : [ '$http', function($http) {
										return $http({
											method : 'GET',
											url : 'rest/people/getAll'
										});
									} ]
								}
							})
							.when('/seating', {
								templateUrl : 'seating.html',
								controller : 'Seating',
								resolve : {
									tables : [ '$http', function($http) {
										return $http({
											method : 'GET',
											url : 'rest/table/getAll'
										});
									} ]
								}
							})
							.when('/addReservation', {
								templateUrl : 'addReservation.html',
								controller : 'AddReservation',
							})
							.when(
									'/editReservation/:id/:firstName/:lastName/:emailId/:date/:time/:phone/:partySize/:occasion',
									{
										templateUrl : 'editReservation.html',
										controller : 'EditReservation'
									})
							.when('/doneReservation', {
								templateUrl : 'doneReservation.html',
								controller : 'DoneReservation',
							})
							.when('/customerDetails/:id', {
								templateUrl : 'customerDetails.html',
								controller : 'CustomerDetails',
							})
							.when('/profile', {
								templateUrl : 'profile.html',
								controller : 'Profile',
								resolve : {
									auto : [ '$http', function($http) {
										return $http({
											method : 'GET',
											url : 'rest/auto/get'
										});
									} ],
									people : [ '$http', function($http) {
										return $http({
											method : 'GET',
											url : 'rest/owner/getAll'
										});
									} ]
								}
							})
							.when('/editProfile/:id', {
								templateUrl : 'editProfile.html',
								controller : 'EditProfile',
							})
							.when('/addProfile', {
								templateUrl : 'addProfile.html',
								controller : 'AddProfile',
							})
							.when('/contacts', {
								templateUrl : 'contacts.html',
								controller : 'Contact',
								resolve : {
									people : [ '$http', function($http) {
										return $http({
											method : 'GET',
											url : 'rest/people/getAll'
										});
									} ]
								}
							})
							.when(
									'/updateOrder/:id/:firstName/:lastName/:emailId/:date/:time/:phone/:partySize/:occasion',
									{
										templateUrl : 'updateOrder.html',
										controller : 'UpdateOrder'
									}).otherwise({
								redirectTo : '/addOrder'
							});
				} ]);