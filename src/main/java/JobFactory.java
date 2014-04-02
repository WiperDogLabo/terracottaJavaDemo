import java.util.Date;
import org.quartz.JobDetail;
import org.quartz.InterruptableJob;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import static org.quartz.JobBuilder.*;

public class JobFactory {
	/**
	 *
	 */
	public static JobDetail createJob(String name) {
		return newJob(GenericJob.class)
			.withIdentity(name)
			.storeDurably(true)
			.build();
	}

	/**
	 *
	 */
	public static JobDetail createConcurrentJob(String name) {
		return newJob(GenericConcurrentJob.class)
			.withIdentity(name)
			.storeDurably(true)
			.build();
	}

	/**
	 *
	 */
	@DisallowConcurrentExecution
	public static class GenericJob implements InterruptableJob {
		public void interrupt() {
		}

		public void execute(JobExecutionContext context) {
			System.out.println ((new Date()).toGMTString() + " Starting the Job:" + context.getJobDetail().getKey().getName() + " .");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {}
			System.out.println ((new Date()).toGMTString() + " Finishing the Job:" + context.getJobDetail().getKey().getName() + " .");
		}
	}

	/**
	 *
	 *
	 */
	public static class GenericConcurrentJob implements InterruptableJob {
		public void interrupt() {
		}

		public void execute(JobExecutionContext context) {
			System.out.println ((new Date()).toGMTString() + " Starting the Job:" + context.getJobDetail().getKey().getName() + " .");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {}
			System.out.println ((new Date()).toGMTString() + " Finishing the Job:" + context.getJobDetail().getKey().getName() + " .");
		}
	}
}

