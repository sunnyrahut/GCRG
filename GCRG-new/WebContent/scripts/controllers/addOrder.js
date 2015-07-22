restaurant.controller('AddOrder', [
		'$scope',
		'$http',
		'$location',
		'auto',
		'table',
		function($scope, $http, $location, auto, table) {

			$scope.addCustomer = function() {
				var obj = {
					"firstName" : $scope.firstName,
					"lastName" : $scope.lastName,
					"email" : $scope.emailId,
					"bookingDate" : $scope.date,
					"bookingTime" : $scope.time,
					"phone" : $scope.phone,
					"partySize" : $scope.partySize,
					"occasion" : $scope.occasion,
				};

				$http({
					method : 'POST',
					url : 'rest/people/add',
					data : obj,
					responseType : 'json'
				}).success(
						function(data, status, headers, config) {
							console.log(data, status, headers, config);
							console.log(data.data.id);
							if (auto.data.data.auto == true && table != null) {
								var d = new Date();
								var obj = {
									"tableNo" : table.data.data.tableNo,
									"bookingId" : data.data.id,
									"tableStatus" : "Occupied",
									"since" : d.getFullYear() + "-"
											+ d.getMonth() + "-" + d.getDate()
											+ " " + d.getHours() + ":"
											+ d.getMinutes() + ":"
											+ d.getSeconds()
								};
								$http({
									method : 'POST',
									url : 'rest/table/update',
									responseType : 'json',
									data : obj
								})
										.success(
												function(data, status, headers,
														config) {
													console.log(data, status,
															headers, config);
												}).error(
												function(data, status, headers,
														config) {
													console.log(data, status,
															headers, config);
												});
							}
						}).error(function(data, status, headers, config) {
					console.log(data, status, headers, config);
				});
			};

			$scope.getCustomer = function() {
				var id = $scope.reservationId;
				var firstName = null;
				var lastName = null;
				var emailId = null;
				var date = null;
				var time = null;
				var phone = null;
				var partySize = null;
				var occasion = null;
				$http({
					method : 'GET',
					url : 'rest/people/get/' + id,
					responseType : 'json'
				})
						.success(
								function(data, status, headers, config) {
									firstName = data.data.firstName;
									lastName = data.data.lastName;
									emailId = data.data.email;
									date = data.data.bookingDate;
									time = data.data.bookingTime;
									phone = data.data.phone;
									partySize = data.data.partySize;
									occasion = data.data.occasion;
									$location.path('/updateOrder/' + id + '/'
											+ firstName + '/' + lastName + '/'
											+ emailId + '/' + date + '/' + time
											+ '/' + phone + '/' + partySize
											+ '/' + occasion);
								}).error(
								function(data, status, headers, config) {
									console.log(data, status, headers, config);
								});
			};

		} ]);
