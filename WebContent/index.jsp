<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>index</title>
<style type="text/css">
	#container {
		min-width: 310px;
		max-width: 800px;
		height: 400px;
		margin: 0 auto
	}
	
	#select {
		min-width: 310px;
		max-width: 800px;
		margin: 0 auto
	}
</style>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/bootstrap-theme.min.css">
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/highcharts/highcharts.js"></script>
<script src="js/highcharts/modules/series-label.js"></script>
<script src="js/highcharts/modules/exporting.js"></script>
<script src="js/highcharts/modules/export-data.js"></script>
<script>
function getChart(currency) {
	
	var buyData = [];
	var sellData = [];
	
	$.getJSON('exchange', 'currency=' + currency, processData)

	function processData(data) {
		$.each(data, function(contact, contactInfo) {
			if(contactInfo.deal == 'buy') {
				buyData.push([Date.UTC(contactInfo.year, contactInfo.month - 1, contactInfo.day), contactInfo.price]);
			} else if(contactInfo.deal == 'sell') {
				sellData.push([Date.UTC(contactInfo.year, contactInfo.month - 1, contactInfo.day), contactInfo.price]);
			}
		});// end each
		
		var titleText = '';
		
		switch (currency) {
		
		case 'USD':
			titleText = '美元匯率';
			break;
			
		case 'CNY':
			titleText = '人民幣匯率';
			break;
			
		case 'ADU':
			titleText = '澳幣匯率';
			break;
			
		case 'ZAR':
			titleText = '南非幣匯率';
			break;
			
		case 'NZD':
			titleText = '紐西蘭幣匯率';
			break;
			
		default:
			break;
		}
		
		Highcharts.chart('container', {

		    title: {
		        text: titleText
		    },
			
			xAxis: {
		        type: 'datetime'
		    },

		    yAxis: {
		        title: {
		            text: titleText += '即期'
		        }
		    },
		    legend: {
		        layout: 'vertical',
		        align: 'right',
		        verticalAlign: 'middle'
		    },

		    plotOptions: {
		        spline: {
		            marker: {
		                enabled: true
		            }
		        }
		    },

		    series: [{
		        name: '賣匯',
		        data: sellData
		    }, {
		    	name: '買匯',
		        data: buyData
		    }],

		    responsive: {
		        rules: [{
		            condition: {
		                maxWidth: 500
		            },
		            chartOptions: {
		                legend: {
		                    layout: 'horizontal',
		                    align: 'center',
		                    verticalAlign: 'bottom'
		                }
		            }
		        }]
		    }

		}); // end Highcharts
	} // end processData
};
</script>
<script>
	$(document).ready(function() {
		
		getChart('USD');
		
		$('#currency').change(function() {
			getChart($('#currency').val());
		})
	}); // end ready
</script>
</head>
<body>
<div id="select">
	<form class="form-inline">
	<strong>幣別：</strong>
	<select id="currency" class="form-control">
		<option value="CNY">人民幣(CNY)
		<option value="USD" selected="selected">美元(USD)
		<option value="AUD">澳幣(AUD)
		<option value="ZAR">南非幣(CNY)
		<option value="NZD">紐西蘭幣(CNY)
	</select>
	</form>
</div>
<div id="container"></div>
</body>

</html>