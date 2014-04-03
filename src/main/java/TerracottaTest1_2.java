
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

public class TerracottaTest1_2 {
	public static void main(String [] args) {
		(new TerracottaTest1_2()).doTest();
	}

	private Scheduler sched = null;

	public void doTest() {
		boolean isClustered = false;
		System.out.println( "Test terracotta 4.1.1" );
		Setup con = new Setup();
		con.initialize(isClustered);
		sched = con.getScheduler();

		testConcurrency1();
	}

	private void testConcurrency1() {
		try {
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


