package currency.alert

class CurrencyUtils {

	static def arrayToSimpleCurrency(currencyArray) {
    	SimpleCurrency currency = new SimpleCurrency()
    	currency.id = currencyArray[0]
    	currency.description = currencyArray[1]
    	return currency
    }
}
