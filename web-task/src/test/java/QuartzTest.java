import java.util.concurrent.TimeUnit;

import job.EchoJobWithJobData;
import org.junit.jupiter.api.Test;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import job.EchoJob;

public class QuartzTest {

    /**
     * 测试启动任务
     */
    @Test
    public void testStartScheduler() {
        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

            JobDetail echoJob =
                JobBuilder.newJob(EchoJob.class).usingJobData("foo", "bar").withIdentity("echo", "default").build();

            SimpleTrigger echoTrigger = TriggerBuilder.newTrigger().withIdentity("echo", "default")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(1).repeatForever()).build();

            scheduler.scheduleJob(echoJob, echoTrigger);
            scheduler.start();

            // 在调用shutdown()之前，给job的触发和执行预留一些时间
            TimeUnit.SECONDS.sleep(3);

            // 在调用scheduler.shutdown()之前，scheduler不会终止
            scheduler.shutdown();
        } catch (SchedulerException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testJobDataMap() {
        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.getContext().put("scheduler-key", "scheduler-val");

            JobDetail echoJob =
                JobBuilder.newJob(EchoJobWithJobData.class).usingJobData("foo", "bar").withIdentity("echo", "default").build();

            SimpleTrigger echoTrigger = TriggerBuilder.newTrigger().usingJobData("trigger-key","trigger-val").withIdentity("echo", "default")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(1).repeatForever()).build();

            scheduler.scheduleJob(echoJob, echoTrigger);
            scheduler.start();

            // 在调用shutdown()之前，给job的触发和执行预留一些时间
            TimeUnit.SECONDS.sleep(3);

            // 在调用scheduler.shutdown()之前，scheduler不会终止
            scheduler.shutdown();
        } catch (SchedulerException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
