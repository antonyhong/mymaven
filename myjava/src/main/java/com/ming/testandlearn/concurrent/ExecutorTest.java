package com.ming.testandlearn.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;

/**
 * Created by ming on 2016/1/19.
 */
public class ExecutorTest {

    public static void main(String[] args) {

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            int i=0;
            @Override
            public void run() {
                while(i++ < 100){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("i:"+i);
                }
            }
        });


        executor.shutdown();
        System.out.println("done!");
    }
}
