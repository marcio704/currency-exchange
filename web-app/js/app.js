var app = angular.module('currency', [ ]);

app.controller('CurrencyController', function($scope, $http) {
	var that = this;
	$scope.amount = 0;
	$scope.currencyFrom = "";
	$scope.currencyTo = "";

	$scope.getConversion = function () {
		$http.post("index/convert", {amount: that.amount.replace(",", "."), currencyFrom: that.currencyFrom, currencyTo: that.currencyTo})
			.success(function(data) {
				$('#convertedAmount').html(data);	
				$('#amountMsg').css('display', 'block');
				$('#amountErrorMsg').css('display', 'none');
			})
			.error(function() {
				$('#convertedAmountError').html('Error! Amount not converted');	
				$('#amountMsg').css('display', 'none');
				$('#amountErrorMsg').css('display', 'block');
			});
	};

	$scope.validateAll = function () {
		if(that.currencyFrom.isEmpty()) {
			that.showErrorMsg('Currency From cannot be empty');
			return false;
		}
		if(that.currencyTo.isEmpty()) {
			that.showErrorMsg('Currency To cannot be empty');
			return false;
		}
		if(that.amount <= 0) {
			that.showErrorMsg('Amount cannot be empty');
			return false;
		}
		return true;
	};

	$scope.showErrorMsg = function (errorMsg) { 
		$('#convertedAmountError').html(errorMsg);	
		$('#amountMsg').css('display', 'none');
		$('#amountErrorMsg').css('display', 'block');
	};

});
