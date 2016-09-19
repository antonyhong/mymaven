package com.hym.myquartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

/**
 * Created by hongyongming on 2016/9/19.
 */
public class MyJob implements org.quartz.Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {


        System.out.println(context.getJobDetail().getKey()+":my job executing ...");
    }
}
