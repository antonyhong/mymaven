package com.ryan.baidu.testandlearn;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by hongyongming on 2016/4/29.
 */
public class TimerTest {

    static Timer timer;
    public static void main(String[] args) throws InterruptedException {
        timer = new Timer();
//        timer.schedule(()->{
//            System.out.println("timer work");
//        },1000);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("timer work");
            }
        },1000,1000);

//        while (true){
//            Thread.sleep(2000);
//            System.out.println("main Thread work");
//        }
        timer.cancel();



    }
}
