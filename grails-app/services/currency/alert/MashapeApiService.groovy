package currency.alert

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

class MashapeApiService {

	def grailsApplication

	ObjectMapper mapper = new ObjectMapper()
    
    def getConversionTax (String fromCurrencyCode, String toCurrencyCode) {
    	def response = sendRequest(getUrlRequestForConversionTax(fromCurrencyCode, toCurrencyCode))

		def json = response.getBody().getObject()

		Currency currency =  mapper.readValue(json.toString(), Currency.class);
		return currency.toAmount
    }

    def getAvailableCurrencies () {
    	def response = sendRequest(getUrlRequestForAvailableCurrencies())

		def list = response.getBody()
		List<SimpleCurrency> parsedList = mapper.readValue(list.toString(), TypeFactory.collectionType(List.class, SimpleCurrency.class));

		return parsedList
    }

    def sendRequest(urlRequest) {
    	HttpResponse<JsonNode> response = Unirest.get(urlRequest)
		.header("X-Mashape-Key", grailsApplication.config.mashape.token)
		.header("Accept", "application/json")
		.asJson();

		return response
    }

    def getUrlRequestForConversionTax (String fromCurrencyCode, String toCurrencyCode) {
    	return """${grailsApplication.config.mashape.url}/?from=${fromCurrencyCode}&from_amount=1&to=${toCurrencyCode}"""
    }

    def getUrlRequestForAvailableCurrencies () {
    	return """${grailsApplication.config.mashape.url}/availablecurrencies"""
    }
}
