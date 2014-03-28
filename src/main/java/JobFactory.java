
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
	private static class GenericJob implements InterruptableJob {
		public void interrupt() {
		}

		public void execute(JobExecutionContext context) {
		}
	}

	/**
	 *
	 *
	 */
	private static class GenericConcurrentJob implements InterruptableJob {
		public void interrupt() {
		}

		public void execute(JobExecutionContext context) {
		}
	}
}

