package com.hym.myquartz;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;
/**
 * Created by hongyongming on 2016/9/19.
 */
public class MyQuartz {
    public static void main(String[] args) throws SchedulerException {
        // Grab the Scheduler instance from the Factory
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();



        // define the job and tie it to our MyJob class
        JobDetail job = newJob(MyJob.class)
                .withIdentity("job1", "group1")
                .build();
        JobDetail job2 = newJob(MyJob.class)
                .withIdentity("job2", "group1")
                .build();

        // Trigger the job to run now, and then repeat every 40 seconds
        Trigger trigger = newTrigger()
                .withIdentity("trigger1", "group1")
                .startNow()
                .withSchedule(simpleSchedule()
                        .withIntervalInSeconds(2)
                        .repeatForever())
                .build();
        Trigger trigger2 = newTrigger()
                .withIdentity("trigger2", "group1")
                .startNow()
                .withSchedule(simpleSchedule()
                        .withIntervalInSeconds(2)
                        .repeatForever())
                .build();

        // Tell quartz to schedule the job using our trigger
        scheduler.scheduleJob(job, trigger);
        scheduler.scheduleJob(job2, trigger2);

        // and start it off
        scheduler.start();
    }



}
