package com.ryan.baidu.testandlearn.sys;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TimerTest {

    public static void main(String[] args) {
        //1、Thread.sleep
        //2、 Timer/TimerTask
        //3、 ScheduledExecutorService
        //4、 quartz
    }




    private static void test2(){
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("hello");
            }
        };
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(timerTask,100,3000);
    }

    private static void test3(){
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.schedule(()->{},1, TimeUnit.MINUTES);
    }
}
