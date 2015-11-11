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
								var fromDate = new Date($scope.fromDate);
								fromDate = fromDate.getUTCFullYear() + '-'
										+ (fromDate.getUTCMonth() + 1) + '-'
										+ fromDate.getUTCDate() + ' '
										+ fromDate.getHours() + ':'
										+ fromDate.getMinutes();
								console.log(fromDate);
								var toDate = new Date($scope.toDate);
								toDate = toDate.getUTCFullYear() + '-'
										+ (toDate.getUTCMonth() + 1) + '-'
										+ (toDate.getUTCDate() - 1) + ' '
										+ toDate.getHours() + ':'
										+ toDate.getMinutes();
								console.log(toDate);
								var allData = [];
								var dataSeries = {
									type : "scatter",
									color : "rgba(54,158,173,.7)"
								};
								var dataPoints = [];
								var param;
								var par = $scope.parameter;
								var freq = $scope.frequencyType;
								var window = $scope.window;
								var convert = false;
								var parameter = $scope.parameter;
								switch (parameter) {
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
									param = "W/m" + "\u2082";
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
									parameter = "CO" + "\u2082";
									param = "mg/m" + "\u00B2" + "/h";
									convert = true;
									break;
								case "h2o_flux":
									parameter = "H" + "\u2082" + "O_flux";
									param = "mg/m" + "\u00B2" + "/h";
									convert = true;
									break;
								case "ch4_flux":
									parameter = "CH" + "\u2084" + "_flux";
									param = "mg/m" + "\u00B2" + "/h";
									convert = true;
									break;
								case "qc_ch4_flux":
									parameter = "qc_CH" + "\u2084" + "_flux";
									param = "mmol/m" + "\u00B2" + "/s";
									break;
								case "co2_mixing_ratio":
									parameter = "CO" + "\u2082"
											+ "_mixing_ratio";
									param = "umol/mol";
									break;
								case "h2o_mixing_ratio":
									parameter = "H" + "\u2082"
											+ "O_mixing_ratio"
									param = "umol/mol";
									break;
								case "ch4_mixing_ratio":
									parameter = "CH" + "\u2084"
											+ "_mixing_ratio"
									param = "umol/mol";
									break;
								case "air_pressure":
									param = "Pa";
									break;
								case "rh":
									param = "%";
									break;
								case "nee_f":
									param = "umol/m" + "\u2082" + "/s";
									break;
								case "nee_fqcOK":
									param = "umol/m" + "\u2082" + "/s";
									break;
								case "le_f":
									param = "W/m" + "\u00B2";
									break;
								case "le_fqcOK":
									param = "--";
									break;
								case "h_f":
									param = "W/m" + "\u00B2";
									break;
								case "h_fqcOK":
									param = "--";
									break;
								case "reco":
									param = "umol/m" + "\u00B2" + "/s";
									break;
								case "gpp_f":
									param = "umol/m" + "\u00B2" + "/s";
									break;
								case "par_AVG_L":
									param = "umol/m" + "\u00B2" + "/s";
									break;
								case "rsolar_AVG_L":
									param = "W/m" + "\u00B2";
									break;
								case "rnet_WC_AVG_L":
									param = "W/m" + "\u00B2";
									break;
								case "air_T_AVG_L":
									param = "degC";
									break;
								case "rh_AVG_L":
									param = "%";
									break;
								case "g_1_AVG_L":
									param = "W/m" + "\u00B2";
									break;
								case "g_2_AVG_L":
									param = "W/m2";
									break;
								case "g_3_AVG_L":
									param = "W/m" + "\u00B2";
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
																if (data.data[i][par] == -9999
																		|| data.data[i][par] == -6999) {
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
																					10)
																			&& i < data.data.length - 1) {
																		sum += data.data[i][par];
																		count++;
																	} else {
																		if (count != 0) {
																			sum = sum
																					/ count;
																		}

																		if (sum != 0) {
																			dataPoints
																					.push({
																						x : new Date(
																								Date
																										.parse(date)),
																						y : sum
																					});
																		}

																		date = data.data[i].timeStamp;
																		sum = data.data[i][par];
																		count = 1;
																	}
																}
															}
														} else if (freq == "hourly") {
															for (var i = 0; i < data.data.length; i++) {
																var par = $scope.parameter;
																if (data.data[i][par] == -9999
																		|| data.data[i][par] == -6999) {
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
														if (window <= dataPoints.length) {
															var dataFinal = [];
															for (var i = Math
																	.floor(window / 2); i < (dataPoints.length - Math
																	.floor(window / 2)); i++) {
																var j = -Math
																		.floor(window / 2);
																var average = 0;
																while (j <= Math
																		.floor(window / 2)) {
																	average += dataPoints[i
																			+ j].y;
																	j++;
																}
																average = average
																		/ window;
																if (par
																		.indexOf("flux") > -1) {
																	if (freq == "daily") {
																		param = "mg C m"
																				+ "\u207B"
																				+ "\u00B2"
																				+ "d"
																				+ "\u207B"
																				+ "\u00B9";
																		console
																				.log("Daily");
																	} else if (freq == "hourly") {
																		console
																				.log("Hourly");
																		param = "mg C m"
																				+ "\u207B"
																				+ "\u00B2"
																				+ "h"
																				+ "\u207B"
																				+ "\u00B9";
																	}
																	dataFinal
																			.push({
																				x : dataPoints[i].x,
																				y : average * 3600 / 1000 * 12
																			});
																} else {
																	dataFinal
																			.push({
																				x : dataPoints[i].x,
																				y : average
																			});
																}
															}
															dataSeries.dataPoints = dataFinal;
															allData
																	.push(dataSeries);
														} else {
															$scope.badSelect = "Please select appropriate time stamps."
														}
														var chart = new CanvasJS.Chart(
																"chartContainer",
																{
																	zoomEnabled : true,
																	title : {
																		text : parameter
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
														if (freq == "daily") {
															var date = data.data[0].timeStamp;
															var sum = 0;
															var count = 0;
															for (var i = 0; i < data.data.length; i++) {
																var par = $scope.parameter;
																if (data.data[i][par] == -9999
																		|| data.data[i][par] == -6999) {
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
																					10)
																			&& i < data.data.length - 1) {
																		count++;
																		sum += data.data[i][par];
																	} else {
																		if (count != 0) {
																			sum = sum
																					/ count;
																		}
																		if (sum != 0) {
																			dataPoints
																					.push({
																						x : new Date(
																								Date
																										.parse(date)),
																						y : sum
																					});
																		}
																		date = data.data[i].timeStamp;
																		sum = data.data[i][par];
																		count = 1;
																	}
																}
															}
														} else if (freq == "hourly") {
															for (var i = 0; i < data.data.length; i++) {
																var par = $scope.parameter;
																if (data.data[i][par] == -9999
																		|| data.data[i][par] == -6999) {
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
														if (window <= dataPoints.length) {
															var dataFinal = [];
															for (var i = Math
																	.floor(window / 2); i < (dataPoints.length - Math
																	.floor(window / 2)); i++) {
																var j = -Math
																		.floor(window / 2);
																var average = 0;
																while (j <= Math
																		.floor(window / 2)) {
																	average += dataPoints[i
																			+ j].y;
																	j++;
																}
																average = average
																		/ window;
																dataFinal
																		.push({
																			x : dataPoints[i].x,
																			y : average
																		})
															}
															dataSeries.dataPoints = dataFinal;
															allData
																	.push(dataSeries);
														} else {
															$scope.badSelect = "Please select appropriate tme stamps."
														}
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
																if (data.data[i][par] == -9999
																		|| data.data[i][par] == -6999) {
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
																					10)
																			&& i < data.data.length - 1) {
																		count++;
																		sum += data.data[i][par];
																	} else {
																		if (count != 0) {
																			sum = sum
																					/ count;
																		}
																		if (sum != 0) {
																			dataPoints
																					.push({
																						x : new Date(
																								Date
																										.parse(date)),
																						y : sum
																					});
																		}
																		date = data.data[i].timeStamp;
																		sum = data.data[i][par];
																		count = 1;
																	}
																}
															}
														} else if (freq == "hourly") {
															for (var i = 0; i < data.data.length; i++) {
																var par = $scope.parameter;
																if (data.data[i][par] == -9999
																		|| data.data[i][par] == -6999) {
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
														if (window <= dataPoints.length) {
															var dataFinal = [];
															for (var i = Math
																	.floor(window / 2); i < (dataPoints.length - Math
																	.floor(window / 2)); i++) {
																var j = -Math
																		.floor(window / 2);
																var average = 0;
																while (j <= Math
																		.floor(window / 2)) {
																	average += dataPoints[i
																			+ j].y;
																	j++;
																}
																average = average
																		/ window;
																if (par == "ppt_TOT_L") {
																	average *= window;
																} else if (par == "swc_1_AVG_L"
																		|| par == "swc_2_AVG_L"
																		|| par == "swc_3_AVG_L"
																		|| par == "swc_4_AVG_L"
																		|| par == "p2_SWC_5_AVG_L"
																		|| par == "p2_SWC_15_AVG_L"
																		|| par == "p2_SWC_30_AVG_L"
																		|| par == "p3_SWC_5_AVG_L"
																		|| par == "p3_SWC_15_AVG_L"
																		|| par == "p3_SWC_30_AVG_L"
																		|| par == "p4_SWC_5_AVG_L"
																		|| par == "p4_SWC_15_AVG_L"
																		|| par == "p4_SWC_30_AVG_L") {
																	average *= 100;
																}
																dataFinal
																		.push({
																			x : dataPoints[i].x,
																			y : average
																		})
															}
															dataSeries.dataPoints = dataFinal;
															allData
																	.push(dataSeries);
														} else {
															$scope.badSelect = "Please select appropriate tme stamps."
														}
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
