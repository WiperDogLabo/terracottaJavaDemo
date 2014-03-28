
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

public class TerracottaConnect {
	public static void main(String [] args) {
		TerracottaConnect con = new TerracottaConnect();
	}

	public TerracottaConnect(){
		System.out.println( "Test terracotta 4.1.1" );
		StdSchedulerFactory sf = new StdSchedulerFactory();
		Properties schedProp = new Properties();
		schedProp.setProperty("org.quartz.scheduler.instanceName", "TestScheduler");
		schedProp.setProperty("org.quartz.scheduler.instanceId", "groovy_instance");
		schedProp.setProperty("org.quartz.scheduler.skipUpdateCheck", "true");
		schedProp.setProperty("org.quartz.threadPool.class", "org.quartz.simpl.SimpleThreadPool");
		schedProp.setProperty("org.quartz.threadPool.threadCount", "1");
		schedProp.setProperty("org.quartz.threadPool.threadPriority", "5");
		schedProp.setProperty("org.quartz.jobStore.misfireThreshold", "60000");
		schedProp.setProperty("org.quartz.jobStore.class", "org.terracotta.quartz.TerracottaJobStore");
		schedProp.setProperty("org.quartz.jobStore.tcConfigUrl", "localhost:9510");
		try {
			sf.initialize(schedProp);
			Scheduler sched = sf.getScheduler();
			
			JobDetail job = JobFactory.createJob("job1");
			sched.addJob(job, true);
			Trigger trigger = TriggerBuilder.newTrigger().forJob(job).startNow().withSchedule(SimpleScheduleBuilder.simpleSchedule().repeatForever().withIntervalInSeconds(10)).build();
			sched.scheduleJob(trigger);
			
			sched.start();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}


