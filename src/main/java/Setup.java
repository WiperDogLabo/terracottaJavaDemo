
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

public class Setup {

	private Scheduler sched = null;

	public void initialize(boolean isClustered) {
		StdSchedulerFactory sf = new StdSchedulerFactory();
		Properties schedProp = new Properties();
		schedProp.setProperty("org.quartz.scheduler.instanceName", "TestScheduler");
		schedProp.setProperty("org.quartz.scheduler.instanceId", "instance1");
		schedProp.setProperty("org.quartz.scheduler.skipUpdateCheck", "true");
		schedProp.setProperty("org.quartz.threadPool.class", "org.quartz.simpl.SimpleThreadPool");
		schedProp.setProperty("org.quartz.threadPool.threadCount", "5");
		schedProp.setProperty("org.quartz.threadPool.threadPriority", "5");
		if (isClustered) {
			schedProp.setProperty("org.quartz.jobStore.misfireThreshold", "60000");
			schedProp.setProperty("org.quartz.jobStore.class", "org.terracotta.quartz.TerracottaJobStore");
			schedProp.setProperty("org.quartz.jobStore.tcConfigUrl", "localhost:9510");
		}
		try {
			sf.initialize(schedProp);
			sched = sf.getScheduler();
			sched.start();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Setup(){
	}

	public Scheduler getScheduler() {
		return sched;
	}
}


