package com.ming.testandlearn.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by hongyongming on 2016/1/29.
 */
public class FutureTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "";
            }
        };
        FutureTask<String> futureTask = new FutureTask<String>(callable);
        Thread t = new Thread(futureTask);
        t.start();
        /**调用get会有阻塞*/
        String result =  futureTask.get();
    }
}
