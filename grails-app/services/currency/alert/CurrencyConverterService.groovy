package currency.alert

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import static org.quartz.JobBuilder.*; 
import static org.quartz.SimpleScheduleBuilder.*; 
import static org.quartz.TriggerBuilder.*; 

class CurrencyConverterService {

	def grailsApplication
   	ObjectMapper mapper = new ObjectMapper()

    def convert (String fromCurrency, String toCurrency, double value) { 
    	def conversionAmount = getConversionTaxFromMashapeAPI(fromCurrency, toCurrency)
    	return value * conversionAmount
    }

    def getConversionTaxFromMashapeAPI (String fromCurrencyCode, String toCurrencyCode) {
    	def response = sendRequest(getConversionTax(fromCurrencyCode, toCurrencyCode))

		def json = response.getBody().getObject()

		Currency currency =  mapper.readValue(json.toString(), Currency.class);
		return currency.toAmount
    }

    def getAvailableCurrenciesFromMashapeAPI () {
    	def response = sendRequest(getAvailableCurrencies())

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

    def getConversionTax (String fromCurrencyCode, String toCurrencyCode) {
    	return """${grailsApplication.config.mashape.url}/?from=${fromCurrencyCode}&from_amount=1&to=${toCurrencyCode}"""
    }

    def getAvailableCurrencies () {
    	return """${grailsApplication.config.mashape.url}/availablecurrencies"""
    }

    def startConversionJob() {
    	SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched = sf.getScheduler();
		sched.start();
		JobDetail job = newJob(ConversionJob.class)
				.withIdentity("conversionJob", "guiato")
				.build();
		Trigger trigger = newTrigger()
				.withIdentity("conversionTrigger", "guiato")
				.startNow()
				.withSchedule(simpleSchedule()
						.withIntervalInSeconds(5)
						.repeatForever())
				.build();		
		sched.scheduleJob(job, trigger);
    }

}
