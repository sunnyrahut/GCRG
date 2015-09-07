gcrg.controller('DisplayGraph', [
		'$scope',
		'$location',
		'dataValues',
		function($scope, $location, dataValues) {
			$scope.generateGraph = function() {
				var timeStamps = [];
				var ch4 = [];
				var toDate = new Date($scope.toDate);
				var fromDate = new Date($scope.fromDate);
				$location.path('/graph')
				for (var i = 0; i < dataValues.data.data.length; i++) {
					if (i % 48 == 0) {
						if ((fromDate <= (new Date(
								dataValues.data.data[i].timeStamp)))
								&& (toDate >= (new Date(
										dataValues.data.data[i].timeStamp)))) {
							timeStamps.push(dataValues.data.data[i].timeStamp);
							if (i > 0) {
								var j = i, sum = 0;
								while (j != 0) {
									sum += dataValues.data.data[j].ch4Ppm;
									j--
								}
								ch4.push(sum / i);
							}
						}
					}
				}
				console.log(timeStamps);
				$scope.labels = timeStamps;
				$scope.series = [ 'CH4 PPM' ];
				$scope.data = [ ch4 ];
			};
		} ]);
