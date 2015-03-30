package currency.alert

import org.codehaus.jackson.annotate.JsonProperty;

class Currency extends SimpleCurrency {

	@JsonProperty("from_amount")
	double fromAmount
	
	@JsonProperty("to_amount")
	double toAmount
	
	String from
	
	String to

	String code

	String id
	String description

    static constraints = {
    }
}
