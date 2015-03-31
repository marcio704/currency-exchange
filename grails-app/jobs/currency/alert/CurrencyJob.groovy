package currency.alert



class CurrencyJob {

	private static final String CURRENCY_FROM = 'USD'
	private static final String CURRENCY_TO = 'BRL'
	private static final double ALERT_IN = 1 //Percentage to send rising notification

	private static int jobExecution = 0
	public static double CURRENT_AMOUNT = 0
	
	def currencyConverterService

    static triggers = {
      simple repeatInterval: 60000l // execute job once in 60 seconds
    }
    
    def group = "guiato"
	def description = "Find for value changes on currencies" 

    def execute() {
    	if(jobExecution == 0) {
    		jobExecution++
    		updateCurrentAmount()
    		println "Initial amount: ${CURRENT_AMOUNT}"
    	} else {
    		if(currencyConverterService.isRisingNotificationSent(CURRENCY_FROM, CURRENCY_TO, 1)) {
        		updateCurrentAmount()
        	}	
    	}
    }

    private void updateCurrentAmount () {
    	CURRENT_AMOUNT = currencyConverterService.convert(CURRENCY_FROM, CURRENCY_TO, 1)	
    }
}
