gcrg
		.controller(
				'DisplayGraph',
				[
						'$scope',
						'$location',
						'$http',
						'auth',
						'$window',
						function($scope, $location, $http, auth, $window) {
							$scope.signOut = function() {
								$window.sessionStorage["userInfo"] = null;
								userInfo = null;
								$location.path('/loginExistingUser');
							};

							$scope.generateGraph = function() {
								var timeStamps = [];
								var parameter = [];
								var toDate = new Date($scope.toDate);
								toDate = toDate.getUTCFullYear() + '-'
										+ (toDate.getUTCMonth() + 1) + '-'
										+ toDate.getUTCDate() + ' '
										+ toDate.getHours() + ':'
										+ toDate.getMinutes();
								console.log(toDate);
								var fromDate = new Date($scope.fromDate);
								fromDate = fromDate.getUTCFullYear() + '-'
										+ (fromDate.getUTCMonth() + 1) + '-'
										+ fromDate.getUTCDate() + ' '
										+ fromDate.getHours() + ':'
										+ fromDate.getMinutes();
								var allData = [];
								var dataSeries = {
									type : "line"
								};
								var dataPoints = [];
								var par = $scope.parameter;
								switch ($scope.dataType) {
								case "atq_no_gap_filled":
									$http(
											{
												method : 'GET',
												url : 'rest/atq/getAllNoGap/'
														+ fromDate + '/'
														+ toDate,
												responseType : 'json'
											})
											.success(
													function(data, status,
															headers, config) {
														console
																.log(
																		data,
																		status,
																		headers,
																		config);
														for (var i = 0; i < data.data.length; i++) {
															var par = $scope.parameter;
															dataPoints
																	.push({
																		x : new Date(
																				Date
																						.parse(data.data[i].timeStamp)),
																		y : data.data[i][par]
																	});
														}
														dataSeries.dataPoints = dataPoints;
														allData
																.push(dataSeries);
														var chart = new CanvasJS.Chart(
																"chartContainer",
																{
																	zoomEnabled : true,
																	title : {
																		text : $scope.parameter
																				+ " vs time stamps"
																	},
																	axisX : {
																		labelAngle : -30
																	},
																	axisY : {
																		includeZero : false
																	},
																	legend : {
																		horizontalAlign : "right",
																		verticalAlign : "center"
																	},
																	data : allData
																// random
																// generator
																// below
																});
														chart.render();
													})
											.error(
													function(data, status,
															headers, config) {
														console
																.log(
																		data,
																		status,
																		headers,
																		config);
													});
									break;
								case "atq_gap_filled":
									$http(
											{
												method : 'GET',
												url : 'rest/atq/getAllGap/'
														+ fromDate + '/'
														+ toDate,
												responseType : 'json'
											})
											.success(
													function(data, status,
															headers, config) {
														console
																.log(
																		data,
																		status,
																		headers,
																		config);
														for (var i = 0; i < data.data.length; i++) {
															var par = $scope.parameter;
															dataPoints
																	.push({
																		x : new Date(
																				Date
																						.parse(data.data[i].timeStamp)),
																		y : data.data[i][par]
																	});
														}
														dataSeries.dataPoints = dataPoints;
														allData
																.push(dataSeries);
														var chart = new CanvasJS.Chart(
																"chartContainer",
																{
																	zoomEnabled : true,
																	title : {
																		text : $scope.parameter
																				+ " vs time stamps"
																	},
																	axisX : {
																		labelAngle : -30
																	},
																	axisY : {
																		includeZero : false
																	},
																	legend : {
																		horizontalAlign : "right",
																		verticalAlign : "center"
																	},
																	data : allData
																// random
																// generator
																// below
																});
														chart.render();
													})
											.error(
													function(data, status,
															headers, config) {
														console
																.log(
																		data,
																		status,
																		headers,
																		config);
													});
									break;
								case "atq_meteorological":
									$http(
											{
												method : 'GET',
												url : 'rest/atq/getAllMeteorological/'
														+ fromDate
														+ '/'
														+ toDate,
												responseType : 'json'
											})
											.success(
													function(data, status,
															headers, config) {
														console
																.log(
																		data,
																		status,
																		headers,
																		config);

														for (var i = 0; i < data.data.length; i++) {
															var par = $scope.parameter;
															dataPoints
																	.push({
																		x : new Date(
																				Date
																						.parse(data.data[i].timeStamp)),
																		y : data.data[i][par]
																	});
														}
														dataSeries.dataPoints = dataPoints;
														allData
																.push(dataSeries);
														var chart = new CanvasJS.Chart(
																"chartContainer",
																{
																	zoomEnabled : true,
																	title : {
																		text : $scope.parameter
																				+ " vs time stamps"
																	},
																	axisX : {
																		labelAngle : -30
																	},
																	axisY : {
																		includeZero : false
																	},
																	legend : {
																		horizontalAlign : "right",
																		verticalAlign : "center"
																	},
																	data : allData
																// random
																// generator
																// below
																});
														chart.render();
													})
											.error(
													function(data, status,
															headers, config) {
														console
																.log(
																		data,
																		status,
																		headers,
																		config);
													});
									break;
								}
							};

							$scope.generateExcel = function() {

								var obj = {
									dataType : $scope.dataType,
									parameter : $scope.parameter
								}
								$http({
									method : 'POST',
									url : 'rest/atq/generateCsv',
									data : obj,
									responseType : 'json'
								})
										.success(
												function(data, status, headers,
														config) {
													console.log(data, status,
															headers, config);
													alert("CSV File Successfully Generated!!");
												}).error(
												function(data, status, headers,
														config) {
													console.log(data, status,
															headers, config);
												});
							};
						} ]);
