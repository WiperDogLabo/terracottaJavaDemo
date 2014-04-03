
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

public class QuartzTest3 {
	public static void main(String [] args) {
		(new QuartzTest3()).doTest();
	}

	private Scheduler sched = null;

	public void doTest() {
		boolean isClustered = false;
		System.out.println( "Test terracotta 4.1.1" );
		Setup con = new Setup();
		con.initialize(isClustered);
		sched = con.getScheduler();

		testConcurrency2();
	}

	private void testConcurrency2() {
		try {
			System.out.println("###########################################################################");
			System.out.println("2 jobs 'job1', 'job2' are scheduled as almost same timing, and runs in almost same timing(not using @DisallowConcurrentExecution)");
			System.out.println("###########################################################################");
			JobDetail job = JobFactory.createConcurrentJob("job1");
			sched.addJob(job, true);
			Trigger trigger = TriggerBuilder.newTrigger().forJob(job).startNow().withSchedule(SimpleScheduleBuilder.simpleSchedule().repeatForever().withIntervalInSeconds(10)).build();
			sched.scheduleJob(trigger);
			
			job = JobFactory.createConcurrentJob("job2");
			sched.addJob(job, true);
			trigger = TriggerBuilder.newTrigger().forJob(job).startNow().withSchedule(SimpleScheduleBuilder.simpleSchedule().repeatForever().withIntervalInSeconds(10)).build();
			sched.scheduleJob(trigger);

			// wait for the jobs be executed
			Thread.sleep(10 * 1000);
			// kill scheduler
			sched.shutdown();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}


