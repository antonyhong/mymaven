package com.ryan.baidu.testandlearn.concurrent;


import java.util.concurrent.TimeUnit;

/**
 * Created by hongyongming on 2016/1/27.
 */
public class ThreadTest {

    public static void main(String[] args) throws InterruptedException {

        testDaemon();
        Thread.sleep(2000);
        System.out.println("main thread");
//        long i=0;
//        System.out.println("begin...");
//        while (i < Long.MAX_VALUE) {
//
//            if (i % 10000 == 0) {
//                i = i + 5;
//            }
//            i++;
//            if (i % 10000000 == 0) {
//                System.out.println(i);
//            }
//        }
       // System.out.println("end ...");
        //testJoin();
    }

    private static void testDaemon(){
        Thread t = new Thread(() -> {
            while (true){
                System.out.println("Daemon = false :"+ System.currentTimeMillis());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });



        Thread daemonT = new Thread(() -> {
            while (true){
                System.out.println("Daemon = true :"+ System.currentTimeMillis());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        t.setName("T-not-daemon");
        t.setDaemon(false);
        daemonT.setName("T-daemon");
        daemonT.setDaemon(true);
        t.start();
        daemonT.start();


    }

    public static void testWait(){

    }



    public static void testIterrupt(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<100;i++){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        //e.printStackTrace();
                        /**interrupt()请求中断这个线程,中断=> 线程会在安全的时候停止线程
                         *
                         */
                        Thread.currentThread().interrupt();
                    }
                    System.out.println("i:"+i);
                }
            }
        });
        thread.interrupt();
        thread.isInterrupted();
        /**守护线程,必须在启动前设置*/
        thread.setDaemon(true);
        thread.start();
//        try {
//            //while(!(ok to proceed))
//            thread.wait();
//            /***do something*/
//            thread.notifyAll();
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        /**对象不安全*/
        //thread.stop();
        /***容易死锁*/
        //thread.suspend();
        Thread.interrupted();
    }

    public static void  testJoin() throws InterruptedException {
        System.out.println("method main begin-----");
        Thread t = new Thread(new Runnable(){
            int i = 0;
            @Override
            public void run()
            {
                while(true)
                {
                    System.out.println(i++);
                    try
                    {
                        TimeUnit.MILLISECONDS.sleep(100);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        });
        t.start();
        /**2秒后会 合并过来*/
        t.join(2000);
        /**不会合并过来*/
        //t.join();
        System.out.println("method main end-----");
    }

}
