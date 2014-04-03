
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

public class TerracottaTest1_1 {
	public static void main(String [] args) {
		(new TerracottaTest1_1()).doTest();
	}

	private Scheduler sched = null;

	public void doTest() {
		boolean isClustered = true;
		System.out.println( "Test terracotta 4.1.1" );
		Setup con = new Setup();
		con.initialize(isClustered);
		sched = con.getScheduler();

		testConcurrency1();
	}

	private void testConcurrency1() {
		try {
			// schedule 2 jobs
			// expects these jobs run on 2 JVM.
			JobDetail job = JobFactory.createJob("job1");
			sched.addJob(job, true);
			Trigger trigger = TriggerBuilder.newTrigger().forJob(job).startNow().withSchedule(SimpleScheduleBuilder.simpleSchedule().repeatForever().withIntervalInSeconds(10)).build();
			sched.scheduleJob(trigger);
			
			job = JobFactory.createJob("job2");
			sched.addJob(job, true);
			trigger = TriggerBuilder.newTrigger().forJob(job).startNow().withSchedule(SimpleScheduleBuilder.simpleSchedule().repeatForever().withIntervalInSeconds(10)).build();
			sched.scheduleJob(trigger);

			// wait for the jobs be executed.
			Thread.sleep(10 * 1000);
			// kill scheduler
			sched.shutdown();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}


