gcrg
    .controller(
        'DisplayGraph', [
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
                var allData1 = [];
                var allData2 = [];
                var allData3 = [];
                var allData4 = [];
                var graphCount = 0;
                $body = $("body");
                $scope.generateGraph = function() {
                    $body.addClass("loading");
                    console.log($scope.fromDate);
                    graphCount++;
                    if (graphCount > 4) {
                        graphCount = 1;
                    }
                    var timeStamps = [];
                    var parameter = [];
                    var fromDate = $scope.fromDate;
                    var toDate = $scope.toDate;
                    console.log(toDate);
                    var dataSeries = {
                        type: "scatter",
                        color: "blue"
                    };
                    var dataPoints = [];
                    var allData = [];
                    var param, name;
                    var par = $scope.parameter;
                    var freq = $scope.frequencyType;
                    var window = $scope.window;
                    var convert = false;
                    var parameter = $scope.parameter;
                    switch (parameter) {
                        case "vpd":
                            name = "Vapor pressure deficit (VPD)"
                            param = "Pa";
                            break;
                        case "windSpeed":
                            name = "Wind speed";
                            param = "m s" + "\u207B" + "\u00B9";
                            break;
                        case "wind_dir":
                            name = "Wind direction";
                            param = "\u00B0";
                            break;
                        case "uStar":
                            name = "Friction velocity (u*)";
                            param = "m s" + "\u207B" + "\u00B9";
                            break;
                        case "h":
                            name = "Sensible heat flux (H)";
                            param = "W m" + "\u207B" + "\u00B2";
                            break;
                        case "qc_H":
                            name = "H quality control flag ";
                            param = "mmol m" + "\u207B" + "\u00B2" + " s" + "\u207B" + "\u00B9";
                            convert = true;
                            break;
                        case "le":
                            name = "Latent heat flux (LE)";
                            param = "W m" + "\u207B" + "\u00B2";
                            break;
                        case "qc_LE":
                            name = "LE quality control flag";
                            param = "mmol m" + "\u207B" + "\u00B2" + " s" + "\u207B" + "\u00B9";
                            break;
                        case "co2_flux":
                            name = "Net ecosystem exchange of CO" + "\u2082" + "(NEE)";
                            parameter = "CO" + "\u2082";
                            param = "\u03BC" + "mol m" + "\u207B" + "\u00B2" + " s" + "\u207B" + "\u00B9";
                            convert = true;
                            break;
                        case "h2o_flux":
                            name = "H" + "\u2082" + "O flux";
                            parameter = "H" + "\u2082" + "O_flux";
                            param = "\u03BC" + "mol m" + "\u207B" + "\u00B2" + " s" + "\u207B" + "\u00B9";
                            convert = true;
                            break;
                        case "ch4_flux":
                            name = "Methane (CH" + "\u2084" + ") flux";
                            parameter = "CH" + "\u2084" + "_flux";
                            param = "\u03BC" + "mol m" + "\u207B" + "\u00B2" + " s" + "\u207B" + "\u00B9";
                            convert = true;
                            break;
                        case "qc_ch4_flux":
                            name = "CH" + "\u2084" + " quality control flag";
                            parameter = "qc_CH" + "\u2084" + "_flux";
                            param = "mmol m" + "\u207B" + "\u00B2" + " s" + "\u207B" + "\u00B9";
                            break;
                        case "co2_mixing_ratio":
                            name = "CO" + "\u2082" + " mixing ratio";
                            parameter = "CO" + "\u2082" + "_mixing_ratio";
                            param = "\u03BC" + "mol mol" + "\u207B" + "\u00B2";
                            break;
                        case "h2o_mixing_ratio":
                            name = "H" + "\u2082" + "O mixing ratio";
                            parameter = "H" + "\u2082" + "O_mixing_ratio"
                            param = "\u03BC" + "mol mol" + "\u207B" + "\u00B2";
                            break;
                        case "ch4_mixing_ratio":
                            name = "CH" + "\u2084" + "mixing ratio";
                            parameter = "CH" + "\u2084" + "_mixing_ratio"
                            param = "\u03BC" + "mol mol" + "\u207B" + "\u00B2";
                            break;
                        case "air_pressure":
                            name = "Barometric pressure A";
                            param = "KPa";
                            break;
                        case "rh":
                            name = "Relative humidity (RH) - LICOR";
                            param = "%";
                            break;
                        case "nee_f":
                            name = "Gap-filled NEE";
                            param = "\u03BC" + "mol m" + "\u207B" + "\u00B2" + " s" + "\u207B" + "\u00B9";
                            break;
                        case "nee_fqcOK":
                            name = "Gap-filled NEE quality control";
                            param = "\u03BC" + "mol m" + "\u207B" + "\u00B2" + " s" + "\u207B" + "\u00B9";
                            break;
                        case "le_f":
                            name = "Gap-filled LE";
                            param = "W m" + "\u207B" + "\u00B2";
                            break;
                        case "le_fqcOK":
                            name = "Gap-filled LE quality control";
                            param = "--";
                            break;
                        case "h_f":
                            name = "Gap-filled H";
                            param = "W m" + "\u207B" + "\u00B2";
                            break;
                        case "h_fqcOK":
                            name = "Gap-filled quality control";
                            param = "--";
                            break;
                        case "reco":
                            name = "Ecosystem respiration (Reco)";
                            param = "\u03BC" + "mol m" + "\u207B" + "\u00B2" + " s" + "\u207B" + "\u00B9";
                            break;
                        case "gpp_f":
                            name = "Gross primary production (GPP)";
                            param = "\u03BC" + "mol m" + "\u207B" + "\u00B2" + " s" + "\u207B" + "\u00B9";
                            break;
                        case "par_AVG_L":
                            name = "Photosynthetically active radiation (PAR)";
                            param = "\u03BC" + "mol m" + "\u207B" + "\u00B2" + " s" + "\u207B" + "\u00B9";
                            break;
                        case "rsolar_AVG_L":
                            name = "Solar radiation (Rsolar)";
                            param = "W m" + "\u207B" + "\u00B2";
                            break;
                        case "rnet_WC_AVG_L":
                            name = "Net radiation (Rnet)";
                            param = "W m" + "\u207B" + "\u00B2";
                            break;
                        case "air_T_AVG_L":
                            name = "Air temperature (Tair)";
                            param = "\u00B0" + "C";
                            break;
                        case "rh_AVG_L":
                            name = "Relative humidity (RH) - Vaisala";
                            param = "%";
                            break;
                        case "g_1_AVG_L":
                            name = "Soil heat flux 1 (G1)";
                            param = "W m" + "\u207B" + "\u00B2";
                            break;
                        case "g_2_AVG_L":
                            name = "Soil heat flux 2 (G2)";
                            param = "W m" + "\u207B" + "\u00B2";
                            break;
                        case "g_3_AVG_L":
                            name = "Soil heat flux 3 (G3)";
                            param = "W m" + "\u207B" + "\u00B2";
                            break;
                        case "ppt_TOT_L":
                            name = "Precipitation";
                            param = "mm";
                            break;
                        case "press_mb_AVG_L":
                            name = "Barometric pressure B";
                            param = "mbar";
                            break;
                        case "ss_Tl_R_AVG_L":
                            name = "Photosynthetically active radiation (PAR) - BF4";
                            param = "\u03BC" + "mol m" + "\u207B" + "\u00B2" + " s" + "\u207B" + "\u00B9";
                            break;
                        case "ss_Dif_R_AVG_L":
                            name = "Diffuse PAR - BF4";
                            param = "\u03BC" + "mol m" + "\u207B" + "\u00B2" + " s" + "\u207B" + "\u00B9";
                            break;
                        case "swc_1_AVG_L":
                            name = "Soil water content 15cm A";
                            param = "%";
                            break;
                        case "swc_2_AVG_L":
                            name = "Soil water content 15cm B";
                            param = "%";
                            break;
                        case "swc_3_AVG_L":
                            name = "Soil water content 15cm C";
                            param = "%";
                            break;
                        case "swc_4_AVG_L":
                            name = "Soil water content 15cm D";
                            param = "%";
                            break;
                        case "p2_SWC_5_AVG_L":
                            name = "Soil water content 5cm A";
                            param = "%";
                            break;
                        case "p2_SWC_15_AVG_L":
                            name = "Soil water content 15cm E";
                            param = "%";
                            break;
                        case "p2_SWC_30_AVG_L":
                            name = "Soil water content 30cm A";
                            param = "%";
                            break;
                        case "p3_SWC_5_AVG_L":
                            name = "Soil water content 5cm B";
                            param = "%";
                            break;
                        case "p3_SWC_15_AVG_L":
                            name = "Soil water content 15cm F";
                            param = "%";
                            break;
                        case "p3_SWC_30_AVG_L":
                            name = "Soil water content 30cm B";
                            param = "%";
                            break;
                        case "p3_SolT5_AVG_L":
                            name = "Soil temperature (Tsoil) 5 cm A";
                            param = "\u00B0" + "C";
                            break;
                        case "p3_SolT15_AVG_L":
                            name = "Soil temperature (Tsoil) 15 cm A";
                            param = "\u00B0" + "C";
                            break;
                        case "p3_SolT30_AVG_L":
                            name = "Soil temperature (Tsoil) 30 cm A";
                            param = "\u00B0" + "C";
                            break;
                        case "p4_SolT5_AVG_L":
                            name = "Soil temperature (Tsoil) 5 cm B";
                            param = "\u00B0" + "C";
                            break;
                        case "p4_SolT15_AVG_L":
                            name = "Soil temperature (Tsoil) 15 cm B";
                            param = "\u00B0" + "C";
                            break;
                        case "p4_SolT30_AVG_L":
                            name = "Soil temperature (Tsoil) 30 cm B";
                            param = "\u00B0" + "C";
                            break;
                        case "soil_1_AVG_L":
                            name = "Soil temperature (Tsoil) surface C";
                            param = "\u00B0" + "C";
                            break;
                        case "soil_2_AVG_L":
                            name = "Soil temperature (Tsoil) 5 cm C";
                            param = "\u00B0" + "C";
                            break;
                        case "soil2_1_AVG_L":
                            name = "Soil temperature (Tsoil) surface D";
                            param = "\u00B0" + "C";
                            break;
                        case "soil2_2_AVG_L":
                            name = "Soil temperature (Tsoil) 5 cm D";
                            param = "\u00B0" + "C";
                            break;
                        case "snowDepth_L":
                            name = "Snow depth";
                            param = "m";
                            break;
                    }
                    switch ($scope.dataType) {
                        case "atq_no_gap_filled_cleaned":
                            $http({
                                method: 'GET',
                                url: 'rest/atq/getAllNoGap/' + fromDate + '/' + toDate,
                                responseType: 'json'
                            })
                                .success(
                                    function(data, status,
                                        headers, config) {
                                        if (data.data != null) {
                                            console.log(data,
                                                status,
                                                headers,
                                                config);
                                            var flag = false;
                                            for (var i = 0; i < data.data.length; i++) {
                                                if (data.data[i][$scope.parameter] != null && data.data[i][$scope.parameter] != 0) {
                                                    flag = true;
                                                }
                                            }

                                            if (flag) {
                                                if (freq == "daily") {
                                                    var date = data.data[0].timeStamp;
                                                    var sum = 0;
                                                    var count = 0;
                                                    for (var i = 0; i < data.data.length; i++) {
                                                        var par = $scope.parameter;
                                                        if (data.data[i][par] <= -5999) {
                                                            console
                                                                .log("-9999 data");
                                                            continue;
                                                        } else if (param == "%" && data.data[i][par] > 100) {
                                                            console
                                                                .log("Invalid data!");
                                                        } else {

                                                            if (date
                                                                .substring(
                                                                    0,
                                                                    10) == data.data[i].timeStamp
                                                                .substring(
                                                                    0,
                                                                    10) && i < data.data.length - 1) {
                                                                sum += data.data[i][par];
                                                                count++;
                                                            } else {
                                                                if (count != 0) {
                                                                    sum = sum / count;
                                                                }

                                                                if (sum != 0) {
                                                                    dataPoints
                                                                        .push({
                                                                            x: new Date(
                                                                                date
                                                                                .split(/[- :]/)[0],
                                                                                date
                                                                                .split(/[- :]/)[1] - 1,
                                                                                date
                                                                                .split(/[- :]/)[2],
                                                                                date
                                                                                .split(/[- :]/)[3],
                                                                                date
                                                                                .split(/[- :]/)[4]),
                                                                            y: sum
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
                                                        if (data.data[i][par] <= -5999) {
                                                            console
                                                                .log("-9999 data");
                                                            continue;
                                                        } else if (param == "%" && data.data[i][par] > 100) {
                                                            console
                                                                .log("Invalid data!");
                                                        } else {
                                                            dataPoints
                                                                .push({
                                                                    x: new Date(
                                                                        data.data[i].timeStamp
                                                                        .split(/[- :]/)[0],
                                                                        data.data[i].timeStamp
                                                                        .split(/[- :]/)[1] - 1,
                                                                        data.data[i].timeStamp
                                                                        .split(/[- :]/)[2],
                                                                        data.data[i].timeStamp
                                                                        .split(/[- :]/)[3],
                                                                        data.data[i].timeStamp
                                                                        .split(/[- :]/)[4]),
                                                                    y: data.data[i][par]
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
                                                            average += dataPoints[i + j].y;
                                                            j++;
                                                        }
                                                        average = average / window;
                                                        if (par
                                                            .indexOf("flux") > -1 && freq == "daily") {
                                                            param = "mg C m" + "\u207B" + "\u00B2" + "d" + "\u207B" + "\u00B9";
                                                            console
                                                                .log("Daily");

                                                            dataFinal
                                                                .push({
                                                                    x: dataPoints[i].x,
                                                                    y: average * 3600 / 1000 * 12
                                                                });
                                                        } else {
                                                            dataFinal
                                                                .push({
                                                                    x: dataPoints[i].x,
                                                                    y: average
                                                                });
                                                        }
                                                    }
                                                    dataSeries.dataPoints = dataFinal;
                                                    allData
                                                        .push(dataSeries);
                                                } else {
                                                    $scope.badSelect = "Please select appropriate time stamps."
                                                }

                                                switch (graphCount) {
                                                    case 1:
                                                        allData1 = allData;
                                                        var chart = new CanvasJS.Chart(
                                                            "chartContainer", {
                                                                zoomEnabled: true,
                                                                title: {
                                                                    text: name
                                                                },
                                                                axisX: {
                                                                    labelAngle: -30
                                                                },
                                                                axisY: {
                                                                    includeZero: false,
                                                                    title: param
                                                                },
                                                                legend: {
                                                                    horizontalAlign: "right",
                                                                    verticalAlign: "center"
                                                                },
                                                                data: allData1
                                                                // random
                                                                // generator
                                                                // below
                                                            });
                                                        chart
                                                            .render();
                                                        break;

                                                    case 2:
                                                        allData2 = allData;
                                                        var chart = new CanvasJS.Chart(
                                                            "chartContainer2", {
                                                                zoomEnabled: true,
                                                                title: {
                                                                    text: name
                                                                },
                                                                axisX: {
                                                                    labelAngle: -30
                                                                },
                                                                axisY: {
                                                                    includeZero: false,
                                                                    title: param
                                                                },
                                                                legend: {
                                                                    horizontalAlign: "right",
                                                                    verticalAlign: "center"
                                                                },
                                                                data: allData2
                                                                // random
                                                                // generator
                                                                // below
                                                            });
                                                        chart
                                                            .render();
                                                        break;

                                                    case 3:
                                                        allData3 = allData;
                                                        var chart = new CanvasJS.Chart(
                                                            "chartContainer3", {
                                                                zoomEnabled: true,
                                                                title: {
                                                                    text: name
                                                                },
                                                                axisX: {
                                                                    labelAngle: -30
                                                                },
                                                                axisY: {
                                                                    includeZero: false,
                                                                    title: param
                                                                },
                                                                legend: {
                                                                    horizontalAlign: "right",
                                                                    verticalAlign: "center"
                                                                },
                                                                data: allData3
                                                                // random
                                                                // generator
                                                                // below
                                                            });
                                                        chart
                                                            .render();
                                                        break;

                                                    case 4:
                                                        allData4 = allData;
                                                        var chart = new CanvasJS.Chart(
                                                            "chartContainer4", {
                                                                zoomEnabled: true,
                                                                title: {
                                                                    text: name
                                                                },
                                                                axisX: {
                                                                    labelAngle: -30
                                                                },
                                                                axisY: {
                                                                    includeZero: false,
                                                                    title: param
                                                                },
                                                                legend: {
                                                                    horizontalAlign: "right",
                                                                    verticalAlign: "center"
                                                                },
                                                                data: allData4
                                                                // random
                                                                // generator
                                                                // below
                                                            });
                                                        chart
                                                            .render();
                                                        break;
                                                }
                                            } else {
                                                switch (graphCount) {
                                                    case 1:
                                                        $(
                                                            '#chartContainer')
                                                            .html(
                                                                "<h1>Bad data!</h1>");
                                                        break;
                                                    case 2:
                                                        $(
                                                            '#chartContainer2')
                                                            .html(
                                                                "<h1>Bad data!</h1>");
                                                        break;
                                                    case 3:
                                                        $(
                                                            '#chartContainer3')
                                                            .html(
                                                                "<h1>Bad data!</h1>");
                                                        break;
                                                    case 4:
                                                        $(
                                                            '#chartContainer4')
                                                            .html(
                                                                "<h1>Bad data!</h1>");
                                                        break;
                                                }
                                            }
                                        } else {
                                            switch (graphCount) {
                                                case 1:
                                                    $(
                                                        '#chartContainer')
                                                        .html(
                                                            "<h1>Bad data!</h1>");
                                                    break;
                                                case 2:
                                                    $(
                                                        '#chartContainer2')
                                                        .html(
                                                            "<h1>Bad data!</h1>");
                                                    break;
                                                case 3:
                                                    $(
                                                        '#chartContainer3')
                                                        .html(
                                                            "<h1>Bad data!</h1>");
                                                    break;
                                                case 4:
                                                    $(
                                                        '#chartContainer4')
                                                        .html(
                                                            "<h1>Bad data!</h1>");
                                                    break;
                                            }
                                        }
                                    })
                                .error(
                                    function(data, status,
                                        headers, config) {
                                        alert("Please select dates greater than 01/01/2004!");
                                        console
                                            .log(
                                                data,
                                                status,
                                                headers,
                                                config);
                                    });
                            break;
                        case "atq_gap_filled":
                            $http({
                                method: 'GET',
                                url: 'rest/atq/getAllGap/' + fromDate + '/' + toDate,
                                responseType: 'json'
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
                                                if (data.data[i][par] <= -5999) {
                                                    console
                                                        .log("-9999 data");
                                                    continue;
                                                } else if (param == "%" && data.data[i][par] > 100) {
                                                    console
                                                        .log("Invalid data!");
                                                } else {

                                                    if (date
                                                        .substring(
                                                            0,
                                                            10) == data.data[i].timeStamp
                                                        .substring(
                                                            0,
                                                            10) && i < data.data.length - 1) {
                                                        count++;
                                                        sum += data.data[i][par];
                                                    } else {
                                                        if (count != 0) {
                                                            sum = sum / count;
                                                        }
                                                        if (sum != 0) {
                                                            dataPoints
                                                                .push({
                                                                    x: new Date(
                                                                        date
                                                                        .split(/[- :]/)[0],
                                                                        date
                                                                        .split(/[- :]/)[1] - 1,
                                                                        date
                                                                        .split(/[- :]/)[2],
                                                                        date
                                                                        .split(/[- :]/)[3],
                                                                        date
                                                                        .split(/[- :]/)[4]),
                                                                    y: sum
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
                                                if (data.data[i][par] <= -5999) {
                                                    console
                                                        .log("-9999 data");
                                                    continue;
                                                } else if (param == "%" && data.data[i][par] > 100) {
                                                    console
                                                        .log("Invalid data!");
                                                } else {
                                                    dataPoints
                                                        .push({
                                                            x: new Date(
                                                                data.data[i].timeStamp
                                                                .split(/[- :]/)[0],
                                                                data.data[i].timeStamp
                                                                .split(/[- :]/)[1] - 1,
                                                                data.data[i].timeStamp
                                                                .split(/[- :]/)[2],
                                                                data.data[i].timeStamp
                                                                .split(/[- :]/)[3],
                                                                data.data[i].timeStamp
                                                                .split(/[- :]/)[4]),
                                                            y: data.data[i][par]
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
                                                    average += dataPoints[i + j].y;
                                                    j++;
                                                }
                                                average = average / window;
                                                dataFinal
                                                    .push({
                                                        x: dataPoints[i].x,
                                                        y: average
                                                    })
                                            }
                                            dataSeries.dataPoints = dataFinal;
                                            allData
                                                .push(dataSeries);
                                        } else {
                                            $scope.badSelect = "Please select appropriate tme stamps."
                                        }
                                        switch (graphCount) {
                                            case 1:
                                                allData1 = allData;
                                                var chart = new CanvasJS.Chart(
                                                    "chartContainer", {
                                                        zoomEnabled: true,
                                                        title: {
                                                            text: name
                                                        },
                                                        axisX: {
                                                            labelAngle: -30
                                                        },
                                                        axisY: {
                                                            includeZero: false,
                                                            title: param
                                                        },
                                                        legend: {
                                                            horizontalAlign: "right",
                                                            verticalAlign: "center"
                                                        },
                                                        data: allData1
                                                        // random
                                                        // generator
                                                        // below
                                                    });
                                                chart.render();
                                                break;

                                            case 2:
                                                allData2 = allData;
                                                var chart = new CanvasJS.Chart(
                                                    "chartContainer2", {
                                                        zoomEnabled: true,
                                                        title: {
                                                            text: name
                                                        },
                                                        axisX: {
                                                            labelAngle: -30
                                                        },
                                                        axisY: {
                                                            includeZero: false,
                                                            title: param
                                                        },
                                                        legend: {
                                                            horizontalAlign: "right",
                                                            verticalAlign: "center"
                                                        },
                                                        data: allData2
                                                        // random
                                                        // generator
                                                        // below
                                                    });
                                                chart.render();
                                                break;

                                            case 3:
                                                allData3 = allData;
                                                var chart = new CanvasJS.Chart(
                                                    "chartContainer3", {
                                                        zoomEnabled: true,
                                                        title: {
                                                            text: name
                                                        },
                                                        axisX: {
                                                            labelAngle: -30
                                                        },
                                                        axisY: {
                                                            includeZero: false,
                                                            title: param
                                                        },
                                                        legend: {
                                                            horizontalAlign: "right",
                                                            verticalAlign: "center"
                                                        },
                                                        data: allData3
                                                        // random
                                                        // generator
                                                        // below
                                                    });
                                                chart.render();
                                                break;

                                            case 4:
                                                allData4 = allData;
                                                var chart = new CanvasJS.Chart(
                                                    "chartContainer4", {
                                                        zoomEnabled: true,
                                                        title: {
                                                            text: name
                                                        },
                                                        axisX: {
                                                            labelAngle: -30
                                                        },
                                                        axisY: {
                                                            includeZero: false,
                                                            title: param
                                                        },
                                                        legend: {
                                                            horizontalAlign: "right",
                                                            verticalAlign: "center"
                                                        },
                                                        data: allData4
                                                        // random
                                                        // generator
                                                        // below
                                                    });
                                                chart.render();
                                                break;
                                        }
                                    })
                                .error(
                                    function(data, status,
                                        headers, config) {
                                        alert("Please select dates greater than 01/03/2015!");
                                        console
                                            .log(
                                                data,
                                                status,
                                                headers,
                                                config);
                                    });
                            break;
                        case "atq_meteorological":
                            $http({
                                method: 'GET',
                                url: 'rest/atq/getAllMeteorological/' + fromDate + '/' + toDate,
                                responseType: 'json'
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
                                                if (data.data[i][par] <= -5999) {
                                                    console
                                                        .log("-9999 data");
                                                    continue;
                                                } else if (param == "%" && data.data[i][par] > 100) {
                                                    console
                                                        .log("Invalid data!");
                                                } else {

                                                    if (date
                                                        .substring(
                                                            0,
                                                            10) == data.data[i].timeStamp
                                                        .substring(
                                                            0,
                                                            10) && i < data.data.length - 1) {
                                                        count++;
                                                        sum += data.data[i][par];
                                                    } else {
                                                        if (count != 0) {
                                                            if (par != "ppt_TOT_L") {
                                                                sum = sum / count;
                                                            }
                                                        }
                                                        if (sum != 0) {
                                                            dataPoints
                                                                .push({
                                                                    x: new Date(
                                                                        date
                                                                        .split(/[- :]/)[0],
                                                                        date
                                                                        .split(/[- :]/)[1] - 1,
                                                                        date
                                                                        .split(/[- :]/)[2],
                                                                        date
                                                                        .split(/[- :]/)[3],
                                                                        date
                                                                        .split(/[- :]/)[4]),
                                                                    y: sum
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
                                                if (data.data[i][par] <= -5999) {
                                                    console
                                                        .log("-9999 data");
                                                    continue;
                                                } else if (param == "%" && data.data[i][par] > 100) {
                                                    console
                                                        .log("Invalid data!");
                                                } else {
                                                    dataPoints
                                                        .push({
                                                            x: new Date(
                                                                data.data[i].timeStamp
                                                                .split(/[- :]/)[0],
                                                                data.data[i].timeStamp
                                                                .split(/[- :]/)[1] - 1,
                                                                data.data[i].timeStamp
                                                                .split(/[- :]/)[2],
                                                                data.data[i].timeStamp
                                                                .split(/[- :]/)[3],
                                                                data.data[i].timeStamp
                                                                .split(/[- :]/)[4]),
                                                            y: data.data[i][par]
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
                                                    average += dataPoints[i + j].y;
                                                    j++;
                                                }
                                                average = average / window;
                                                if (par == "ppt_TOT_L") {
                                                    average *= window;
                                                } else if (par == "swc_1_AVG_L" || par == "swc_2_AVG_L" || par == "swc_3_AVG_L" || par == "swc_4_AVG_L" || par == "p2_SWC_5_AVG_L" || par == "p2_SWC_15_AVG_L" || par == "p2_SWC_30_AVG_L" || par == "p3_SWC_5_AVG_L" || par == "p3_SWC_15_AVG_L" || par == "p3_SWC_30_AVG_L" || par == "p4_SWC_5_AVG_L" || par == "p4_SWC_15_AVG_L" || par == "p4_SWC_30_AVG_L") {
                                                    average *= 100;
                                                } else if (par == "par_AVG_L" && freq == "daily") {
                                                    param = "mol m" + "\u207B" + "\u00B2" + " d" + "\u207B" + "\u00B9";
                                                    average = average * 60 * 60 * 24 * 0.000001
                                                }
                                                dataFinal
                                                    .push({
                                                        x: dataPoints[i].x,
                                                        y: average
                                                    })
                                            }
                                            dataSeries.dataPoints = dataFinal;
                                            allData
                                                .push(dataSeries);
                                        } else {
                                            $scope.badSelect = "Please select appropriate tme stamps."
                                        }
                                        switch (graphCount) {
                                            case 1:
                                                allData1 = allData;
                                                var chart = new CanvasJS.Chart(
                                                    "chartContainer", {
                                                        zoomEnabled: true,
                                                        title: {
                                                            text: name
                                                        },
                                                        axisX: {
                                                            labelAngle: -30
                                                        },
                                                        axisY: {
                                                            includeZero: false,
                                                            title: param
                                                        },
                                                        legend: {
                                                            horizontalAlign: "right",
                                                            verticalAlign: "center"
                                                        },
                                                        data: allData1
                                                        // random
                                                        // generator
                                                        // below
                                                    });
                                                chart.render();
                                                break;

                                            case 2:
                                                allData2 = allData;
                                                var chart = new CanvasJS.Chart(
                                                    "chartContainer2", {
                                                        zoomEnabled: true,
                                                        title: {
                                                            text: name
                                                        },
                                                        axisX: {
                                                            labelAngle: -30
                                                        },
                                                        axisY: {
                                                            includeZero: false,
                                                            title: param
                                                        },
                                                        legend: {
                                                            horizontalAlign: "right",
                                                            verticalAlign: "center"
                                                        },
                                                        data: allData2
                                                        // random
                                                        // generator
                                                        // below
                                                    });
                                                chart.render();
                                                break;

                                            case 3:
                                                allData3 = allData;
                                                var chart = new CanvasJS.Chart(
                                                    "chartContainer3", {
                                                        zoomEnabled: true,
                                                        title: {
                                                            text: name
                                                        },
                                                        axisX: {
                                                            labelAngle: -30
                                                        },
                                                        axisY: {
                                                            includeZero: false,
                                                            title: param
                                                        },
                                                        legend: {
                                                            horizontalAlign: "right",
                                                            verticalAlign: "center"
                                                        },
                                                        data: allData3
                                                        // random
                                                        // generator
                                                        // below
                                                    });
                                                chart.render();
                                                break;

                                            case 4:
                                                allData4 = allData;
                                                var chart = new CanvasJS.Chart(
                                                    "chartContainer4", {
                                                        zoomEnabled: true,
                                                        title: {
                                                            text: name
                                                        },
                                                        axisX: {
                                                            labelAngle: -30
                                                        },
                                                        axisY: {
                                                            includeZero: false,
                                                            title: param
                                                        },
                                                        legend: {
                                                            horizontalAlign: "right",
                                                            verticalAlign: "center"
                                                        },
                                                        data: allData4
                                                        // random
                                                        // generator
                                                        // below
                                                    });
                                                chart.render();
                                                break;
                                        }
                                    })
                                .error(
                                    function(data, status,
                                        headers, config) {
                                        alert("Please select dates greater than 01/03/2015!");
                                        console
                                            .log(
                                                data,
                                                status,
                                                headers,
                                                config);
                                    });
                            break;
                    }
                    $body.removeClass("loading");
                };

                $scope.generateExcel = function() {
                    $body.addClass("loading");
                    var fromDate = new Date($scope.fromDate);
                    fromDate = fromDate.getUTCFullYear() + '-' + (fromDate.getUTCMonth() + 1) + '-' + fromDate.getUTCDate() + ' ' + fromDate.getHours() + ':' + fromDate.getMinutes();
                    console.log(fromDate);
                    var toDate = new Date($scope.toDate);
                    toDate = toDate.getUTCFullYear() + '-' + (toDate.getUTCMonth() + 1) + '-' + (toDate.getUTCDate()) + ' ' + toDate.getHours() + ':' + toDate.getMinutes();
                    console.log(toDate);

                    var obj = {
                        dataType: $scope.dataType,
                        timeStampTo: toDate,
                        timeStampFrom: fromDate
                    }
                    $http({
                        method: 'POST',
                        url: 'rest/atq/generateCsv',
                        data: obj,
                        responseType: 'json'
                    })
                        .success(
                            function(data, status, headers,
                                config) {
                                console.log(data, status,
                                    headers, config);
                                switch ($scope.dataType) {
                                    case "atq_no_gap_filled_cleaned":
                                        var fullData = "Time Stamp,Wind Speed, Wind Direction, U*, H, qc_H, LE, qc_LE, CO2_Flux, H2O_Flux, CH4_Flux, qc_CH4_Flux, co2_mixing_ratio, h2o_mixing_ratio, ch4_mixing_ratio, air_pressure, RH" + "\n";
                                        for (var i = 0; i < data.data.length; i++) {
                                            fullData += data.data[i].timeStamp + "," + data.data[i].windSpeed + "," + data.data[i].wind_dir + "," + data.data[i].uStar + "," + data.data[i].h + "," + data.data[i].qc_H + "," + data.data[i].le + "," + data.data[i].qc_LE + "," + data.data[i].co2_flux + "," + data.data[i].h2o_flux + "," + data.data[i].ch4_flux + "," + data.data[i].qc_ch4_flux + "," + data.data[i].co2_mixing_ratio + "," + data.data[i].h2o_mixing_ratio + "," + data.data[i].ch4_mixing_ratio + "," + data.data[i].air_pressure + "," + data.data[i].rh + "\n";
                                        }
                                        break;
                                    case "atq_gap_filled":
                                        var fullData = "Time Stamp,nee_f,nee_fqcOK,le_f,le_fqcOK,h_f,h_fqcOK,reco,gpp_f" + "\n";
                                        for (var i = 0; i < data.data.length; i++) {
                                            fullData += data.data[i].timeStamp + "," + data.data[i].nee_f + "," + data.data[i].nee_fqcOK + "," + data.data[i].le_f + "," + data.data[i].le_fqcOK + "," + data.data[i].h_f + "," + data.data[i].h_fqcOK + "," + data.data[i].reco + "," + data.data[i].gpp_f + "\n";
                                        }
                                        break;
                                    case "atq_meteorological":
                                        var fullData = "Time Stamp,par_AVG_L,rsolar_AVG_L,rnet_WC_AVG_L,air_T_AVG_L,rh_AVG_L,g_1_AVG_L,g_2_AVG_L,g_3_AVG_L,ppt_TOT_L,press_mb_AVG_L,ss_Tl_R_AVG_L,ss_Dif_R_AVG_L,swc_1_AVG_L,swc_2_AVG_L,swc_3_AVG_L,swc_4_AVG_L,p2_SWC_5_AVG_L,p2_SWC_15_AVG_L,p2_SWC_30_AVG_L,p3_SWC_5_AVG_L,p3_SWC_15_AVG_L,p3_SWC_30_AVG_L,p3_SoilT5_AVG_L,p3_SoilT15_AVG_L,p3_SoilT30_AVG_L,p4_SoilT5_AVG_L,p4_SoilT15_AVG_L,p4_SoilT30_AVG_L,snowDepth_L,Soil_1_AVG_L,Soil_2_AVG_L,Soil2_1_AVG_L,Soil2_2_AVG_L" + "\n";
                                        for (var i = 0; i < data.data.length; i++) {
                                            fullData += data.data[i].timeStamp + "," + data.data[i].par_AVG_L + "," + data.data[i].rsolar_AVG_L + "," + data.data[i].rnet_WC_AVG_L + "," + data.data[i].air_T_AVG_L + "," + data.data[i].rh_AVG_L + "," + data.data[i].g_1_AVG_L + "," + data.data[i].g_2_AVG_L + "," + data.data[i].g_3_AVG_L + "," + data.data[i].ppt_TOT_L + "," + data.data[i].press_mb_AVG_L + "," + data.data[i].ss_Tl_R_AVG_L + "," + data.data[i].ss_Dif_R_AVG_L + "," + data.data[i].swc_1_AVG_L + "," + data.data[i].swc_2_AVG_L + "," + data.data[i].swc_3_AVG_L + "," + data.data[i].swc_4_AVG_L + "," + data.data[i].p2_SWC_5_AVG_L + "," + data.data[i].p2_SWC_15_AVG_L + "," + data.data[i].p2_SWC_30_AVG_L + "," + data.data[i].p3_SWC_5_AVG_L + "," + data.data[i].p3_SWC_15_AVG_L + "," + data.data[i].p3_SWC_30_AVG_L + "," + data.data[i].p3_SolT5_AVG_L + "," + data.data[i].p3_SolT15_AVG_L + "," + data.data[i].p3_SolT30_AVG_L + "," + data.data[i].p4_SolT5_AVG_L + "," + data.data[i].p4_SolT15_AVG_L + "," + data.data[i].p4_SolT30_AVG_L + "," + data.data[i].snowDepth_L + "\n" + data.data[i].Soil_1_AVG_L + "\n" + data.data[i].Soil_2_AVG_L + "\n" + data.data[i].Soil2_1_AVG_L + "\n" + data.data[i].Soil2_2_AVG_L + "\n";
                                        }
                                        break;
                                }
                                download(fullData,
                                    $scope.dataType + ".csv",
                                    "text/plain");
                            }).error(
                            function(data, status, headers,
                                config) {
                                console.log(data, status,
                                    headers, config);
                            });
                            $body.removeClass("loading");
                };
            }
        ]);
