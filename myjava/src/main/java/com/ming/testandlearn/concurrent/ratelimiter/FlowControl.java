package com.ming.testandlearn.concurrent.ratelimiter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by HONGYONGMING on 2016/10/24.
 */
public class FlowControl {


    /**
     * 使用 Semphore 进行流控
     */
    //Semaphore semaphore = new Semaphore(10);

    final  static int Threshold = 100;
    public static void main(String[] args) {
        ExecutorService excute = Executors.newCachedThreadPool();

        final Semaphore semp = new Semaphore(Threshold);

        for (int i = 0; i < 500000; i++) {
            final int threadId = i;
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    try {
                        if (semp.tryAcquire()) {
                            System.out.println("Accessing:" + threadId);
                            Thread.sleep((long) (Math.random() * 1000));

                            semp.release();

                          //System.out.println("-----------------" + semp.availablePermits());
                        }
                        else {
                            System.out.println("Accessing loose:" + threadId);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            excute.execute(run);
        }
        excute.shutdown();

    }
}
