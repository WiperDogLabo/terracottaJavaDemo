
import java.util.Properties;

import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.Trigger;
import org.quartz.SimpleScheduleBuilder;

public class QuartzTest5 {
	public static void main(String [] args) {
		(new QuartzTest5()).doTest();
	}

	private Scheduler sched = null;

	public void doTest() {
		boolean isClustered = false;
		System.out.println( "Test terracotta 4.1.1" );
		Setup con = new Setup();
		con.initialize(isClustered);
		sched = con.getScheduler();

		testConcurrency4();
	}

	private void testConcurrency4() {
		try {
			JobDetail job = JobFactory.createConcurrentJob("job1");
			sched.addJob(job, true);
			Trigger trigger = TriggerBuilder.newTrigger().forJob(job).startNow().withSchedule(SimpleScheduleBuilder.simpleSchedule().repeatForever().withIntervalInSeconds(10)).build();
			sched.scheduleJob(trigger);

			trigger = TriggerBuilder.newTrigger().forJob(job).startNow().withSchedule(SimpleScheduleBuilder.simpleSchedule().repeatForever().withIntervalInSeconds(10)).build();
			sched.scheduleJob(trigger);

			// wait for the jobs be executed.
			Thread.sleep(10 * 1000);
			// kill the scheduler.
			sched.shutdown();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}


