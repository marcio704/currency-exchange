package currency.alert

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

class HipchatApiService {

	def grailsApplication

    def sendMessage(message) {
    	def encodedMsg = URLEncoder.encode(message, "UTF-8")
    	
    	HttpResponse<JsonNode> response = 
    		Unirest.get("${grailsApplication.config.hipchat.url}?format=json&auth_token=${grailsApplication.config.hipchat.token}&room_id=${grailsApplication.config.hipchat.room}&from=currencyAlert&color=green&message=${encodedMsg}")
    		.asJson();
		return response
    }
}
