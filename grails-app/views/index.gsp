<html lang="en" ng-app="currency">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Bootstrap 101 Template</title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <g:javascript src="angular.min.js"></g:javascript>
  	<g:javascript src="app.js"></g:javascript>
  	<g:javascript src="jquery.mask.min.js"></g:javascript>

  </head>
  <body>
  	
    <div class="container" ng-controller="CurrencyController as currencyCtrl">
    	<h1>{{"Hello, from Currency " + "Alert!"}}</h1>
	    <div class="row">
	    	<div class="col-md-6">
		    	<h4><span class="label label-default">From: </span></h4>
		    	<div class="dropdown">
		    		<g:select
		    			class="dropdown-menu"
                        name="currencyFromSelect"
                        multiple="false"
                        class="form-control"
                        style="height: 100px;"
                        ng-model="currencyCtrl.currencyFrom"
                        from="${availableCurrencies}" 
                        />
				</div>
			</div>
	    </div>
	    <div class="row">
	    	<div class="col-md-6">
		    	<h4><span class="label label-default">To: </span></h4>
		    	<div class="dropdown">
					<g:select
		    			class="dropdown-menu"
                        name="currencyToSelect"
                        multiple="false"
                        class="form-control"
                        style="height: 100px;"
                        ng-model="currencyCtrl.currencyTo"
                        from="${availableCurrencies}"
                        />
				</div>
			</div>
	    </div>
	    <br />
	    <div class="row">
		    <div class="col-md-6">
		    	<div class="col-md-10">
			    	<div class="input-group">
						<span class="input-group-addon">$</span>
						<input class="form-control" aria-label="Amount (to the nearest dollar)" name="amount" 
							ng-model="currencyCtrl.amount" id="amount">
					</div>
				</div>
				<div class="col-md-2">
					<button class="btn btn-primary" ng-click="getConversion()">Convert!</button>
				</div>
		    </div>
	    </div>
	    <br />
	    <div class="row">
		    <div class="col-md-6">
		    	<div class="alert alert-success" role="alert" id="amountMsg" style="display: none">
		    		The converted value is: <spam id="convertedAmount"></spam>
		    	</div>
		    </div>
	    </div>
	    <div class="row">
		    <div class="col-md-6">
		    	<div class="alert alert-danger" role="alert" id="amountErrorMsg" style="display: none">
		    		<spam id="convertedAmountError"></spam>
		    	</div>
		    </div>
	    </div>
    </div>
    <script type="text/javascript">
		$(document).ready(function() {
		    	$('#amount').mask('000.000.000.000.000,00', {reverse: true});
		});
    </script>>
  </body>
</html>