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
								var param;
								var par = $scope.parameter;
								var freq = $scope.frequencyType;
								switch ($scope.parameter) {
								case "vpd":
									param = "Pa";
									break;
								case "windSpeed":
									param = "m/s";
									break;
								case "wind_dir":
									param = "degree";
									break;
								case "uStar":
									param = "m/s";
									break;
								case "h":
									param = "W/m2";
									break;
								case "qc_H":
									param = "mmol/m2/s";
									break;
								case "le":
									param = "W/m2";
									break;
								case "qc_LE":
									param = "mmol/m2/s";
									break;
								case "co2_flux":
									param = "umol/m2/s";
									break;
								case "h2o_flux":
									param = "umol/m2/s";
									break;
								case "ch4_flux":
									param = "umol/m2/s";
									break;
								case "qc_ch4_flux":
									param = "mmol/m2/s";
									break;
								case "co2_mixing_ratio":
									param = "umol/mol";
									break;
								case "h2o_mixing_ratio":
									param = "umol/mol";
									break;
								case "ch4_mixing_ratio":
									param = "umol/mol";
									break;
								case "air_pressure":
									param = "Pa";
									break;
								case "rh":
									param = "%";
									break;
								case "nee_f":
									param = "umol/m2/s";
									break;
								case "nee_fqcOK":
									param = "umol/m2/s";
									break;
								case "le_f":
									param = "W/m2";
									break;
								case "le_fqcOK":
									param = "--";
									break;
								case "h_f":
									param = "W/m2";
									break;
								case "h_fqcOK":
									param = "--";
									break;
								case "reco":
									param = "umol/m2/s";
									break;
								case "gpp_f":
									param = "umol/m2/s";
									break;
								case "par_AVG_L":
									param = "";
									break;
								case "rsolar_AVG_L":
									param = "W/m2";
									break;
								case "rnet_WC_AVG_L":
									param = "W/m2";
									break;
								case "air_T_AVG_L":
									param = "degC";
									break;
								case "rh_AVG_L":
									param = "%";
									break;
								case "g_1_AVG_L":
									param = "W/m2";
									break;
								case "g_2_AVG_L":
									param = "W/m2";
									break;
								case "g_3_AVG_L":
									param = "W/m2";
									break;
								case "ppt_TOT_L":
									param = "mm";
									break;
								case "press_mb_AVG_L":
									param = "mbar";
									break;
								case "ss_Tl_R_AVG_L":
									param = "";
									break;
								case "ss_Dif_R_AVG_L":
									param = "";
									break;
								case "swc_1_AVG_L":
									param = "%";
									break;
								case "swc_2_AVG_L":
									param = "%";
									break;
								case "swc_3_AVG_L":
									param = "%";
									break;
								case "swc_4_AVG_L":
									param = "%";
									break;
								case "p2_SWC_5_AVG_L":
									param = "%";
									break;
								case "p2_SWC_15_AVG_L":
									param = "%";
									break;
								case "p2_SWC_30_AVG_L":
									param = "%";
									break;
								case "p3_SWC_5_AVG_L":
									param = "%";
									break;
								case "p3_SWC_15_AVG_L":
									param = "%";
									break;
								case "p3_SWC_30_AVG_L":
									param = "%";
									break;
								case "p3_SolT5_AVG_L":
									param = "degC";
									break;
								case "p3_SolT15_AVG_L":
									param = "degC";
									break;
								case "p3_SolT30_AVG_L":
									param = "degC";
									break;
								case "p4_SolT5_AVG_L":
									param = "degC";
									break;
								case "p4_SolT15_AVG_L":
									param = "degC";
									break;
								case "p4_SolT30_AVG_L":
									param = "degC";
									break;
								case "snowDepth_L":
									param = "m";
									break;
								}
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

														if (freq == "daily") {
															var date = data.data[0].timeStamp;
															var sum = 0;
															var count = 0;
															for (var i = 0; i < data.data.length; i++) {
																var par = $scope.parameter;
																// console
																// .log(data.data[i][par]);
																if (data.data[i][par] == -9999) {
																	console
																			.log("-9999 data");
																	continue;
																} else {

																	if (date
																			.substring(
																					0,
																					10) == data.data[i].timeStamp
																			.substring(
																					0,
																					10)) {
																		count++;
																		sum += data.data[i][par];
																		continue;
																	} else {
																		sum = sum
																				/ count
																				* 24;
																		dataPoints
																				.push({
																					x : new Date(
																							Date
																									.parse(date)),
																					y : sum
																				});
																		date = data.data[i].timeStamp;
																		sum = 0;
																	}
																}
															}
														} else if (freq == "hourly") {
															for (var i = 0; i < data.data.length; i++) {
																var par = $scope.parameter;
																if (data.data[i][par] == -9999) {
																	console
																			.log("-9999 data");
																	continue;
																} else {
																	dataPoints
																			.push({
																				x : new Date(
																						Date
																								.parse(data.data[i].timeStamp)),
																				y : data.data[i][par]
																			});
																}
															}
														}

														dataSeries.dataPoints = dataPoints;
														allData
																.push(dataSeries);
														console.log(allData);
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
																		includeZero : false,
																		title : param
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
														console
																.log(data.data[0]);
														if (freq == "daily") {
															var date = data.data[0].timeStamp;
															var sum = 0;
															var count = 0;
															for (var i = 0; i < data.data.length; i++) {
																var par = $scope.parameter;
																// console
																// .log(data.data[i][par]);
																if (data.data[i][par] == -9999) {
																	console
																			.log("-9999 data");
																	continue;
																} else {

																	if (date
																			.substring(
																					0,
																					10) == data.data[i].timeStamp
																			.substring(
																					0,
																					10)) {
																		count++;
																		sum += data.data[i][par];
																		continue;
																	} else {
																		sum = sum
																				/ count
																				* 24;
																		dataPoints
																				.push({
																					x : new Date(
																							Date
																									.parse(date)),
																					y : sum
																				});
																		date = data.data[i].timeStamp;
																		sum = 0;
																	}
																}
															}
														} else if (freq == "hourly") {
															for (var i = 0; i < data.data.length; i++) {
																var par = $scope.parameter;
																if (data.data[i][par] == -9999) {
																	console
																			.log("-9999 data");
																	continue;
																} else {
																	dataPoints
																			.push({
																				x : new Date(
																						Date
																								.parse(data.data[i].timeStamp)),
																				y : data.data[i][par]
																			});
																}
															}
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
																		includeZero : false,
																		title : param
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

														if (freq == "daily") {
															var date = data.data[0].timeStamp;
															var sum = 0;
															var count = 0;
															for (var i = 0; i < data.data.length; i++) {
																var par = $scope.parameter;
																// console
																// .log(data.data[i][par]);
																if (data.data[i][par] == -9999) {
																	console
																			.log("-9999 data");
																	continue;
																} else {

																	if (date
																			.substring(
																					0,
																					10) == data.data[i].timeStamp
																			.substring(
																					0,
																					10)) {
																		count++;
																		sum += data.data[i][par];
																		continue;
																	} else {
																		sum = sum
																				/ count
																				* 24;
																		dataPoints
																				.push({
																					x : new Date(
																							Date
																									.parse(date)),
																					y : sum
																				});
																		date = data.data[i].timeStamp;
																		sum = 0;
																	}
																}
															}
														} else if (freq == "hourly") {
															for (var i = 0; i < data.data.length; i++) {
																var par = $scope.parameter;
																if (data.data[i][par] == -9999) {
																	console
																			.log("-9999 data");
																	continue;
																} else {
																	dataPoints
																			.push({
																				x : new Date(
																						Date
																								.parse(data.data[i].timeStamp)),
																				y : data.data[i][par]
																			});
																}
															}
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
																		includeZero : false,
																		title : param
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
