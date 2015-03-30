package currency.alert

class IndexController {

    def currencyConverterService

    def index() {
    	List<SimpleCurrency> availableCurrencies = currencyConverterService.getAvailableCurrenciesFromMashapeAPI()
    	render(view: "/index", model: ['availableCurrencies': availableCurrencies])
    }

    def convert() {
    	SimpleCurrency currencyFrom = CurrencyUtils.arrayToSimpleCurrency(request.JSON.currencyFrom)
    	SimpleCurrency currencyTo = CurrencyUtils.arrayToSimpleCurrency(request.JSON.currencyTo)
    	def amount = request.JSON.amount
    	
    	float convertedAmount = currencyConverterService.convert(currencyFrom.shortenedId(), currencyTo.shortenedId(), amount)
    	render "${convertedAmount}"
    }
}
