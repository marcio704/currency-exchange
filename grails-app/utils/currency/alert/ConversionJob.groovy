package currency.alert

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

class ConversionJob implements Job {

	def currencyConverterService
	
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		try {
			doExecute();
		} catch ( Exception  ex) {
			ex.printStackTrace();
		}		
	}

	private void doExecute() {
		println currencyConverterService.convert('BRL', 'USD', 1)

	}
}
