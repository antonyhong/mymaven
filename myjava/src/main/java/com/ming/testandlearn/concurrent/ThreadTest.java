package com.ming.testandlearn.concurrent;

import org.apache.el.parser.AstFalse;
import sun.text.resources.cldr.ur.FormatData_ur;

/**
 * Created by hongyongming on 2016/1/27.
 */
public class ThreadTest {

    public static void main(String[] args) {
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

}
