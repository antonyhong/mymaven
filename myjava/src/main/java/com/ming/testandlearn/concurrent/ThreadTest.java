package com.ming.testandlearn.concurrent;

import org.apache.el.parser.AstFalse;
import sun.text.resources.cldr.ur.FormatData_ur;

import java.util.concurrent.TimeUnit;

/**
 * Created by hongyongming on 2016/1/27.
 */
public class ThreadTest {

    public static void main(String[] args) throws InterruptedException {
        testJoin();
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
        //t.join(2000);
        /**不会合并过来*/
        t.join();
        System.out.println("method main end-----");
    }

}
