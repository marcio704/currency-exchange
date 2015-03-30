package currency.alert

import org.codehaus.jackson.annotate.JsonProperty;

class SimpleCurrency {
	public static final double LAST_VALUE = 0

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
