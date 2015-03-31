package currency.alert

class CurrencyConverterService {

	def mashapeApiService
	def hipchatApiService
   	
   	

    def convert (String fromCurrency, String toCurrency, double value) { 
    	def conversionAmount = mashapeApiService.getConversionTax(fromCurrency, toCurrency)
    	return value * conversionAmount
    }

    def getAvailableCurrencies () {
		return mashapeApiService.getAvailableCurrencies()
    }

    def isRisingNotificationSent (currencyFrom, currencyTo, amount) {
    	def newAmount = convert(currencyFrom, currencyTo, amount)
    	def diff = newAmount - CurrencyJob.CURRENT_AMOUNT
    	
    	if(diff >= CurrencyJob.CURRENT_AMOUNT * CurrencyJob.ALERT_IN/100) {
    		String msg = "Alert! ${currencyFrom} to ${currencyTo} has increased from ${CurrencyJob.CURRENT_AMOUNT} to ${newAmount}. Total ${(diff/CurrencyJob.CURRENT_AMOUNT)*100}%" 
    		hipchatApiService.sendMessage(msg)
    		return true	
    	}
    	return false
    }

    
}
