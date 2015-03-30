package currency.alert

import org.codehaus.jackson.annotate.JsonProperty;

class SimpleCurrency {

	String id
	String description

	String toString() {
		return description
	}

	String shortenedId() {
		return id?.substring(0, 3)
	}

    static constraints = {
    }
}
