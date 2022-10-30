package job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.quartz.TriggerKey;

public class EchoJobWithJobData implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("echoJob executing......");
        JobKey jobKey = context.getJobDetail().getKey();
        System.out.println("jobKey = " + jobKey);

        String foo = context.getJobDetail().getJobDataMap().getString("foo");
        System.out.println("jobDetailKey = " + foo);

        TriggerKey triggerKey1 = context.getTrigger().getKey();
        System.out.println("triggerKey1 = " + triggerKey1);

        String triggerKey = context.getTrigger().getJobDataMap().getString("trigger-key");
        System.out.println("triggerKey = " + triggerKey);

        try {
            String schedulerKey = context.getScheduler().getContext().getString("scheduler-key");
            System.out.println("schedulerKey = " + schedulerKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
