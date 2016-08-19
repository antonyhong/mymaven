package com.ming.testandlearn.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by hongyongming on 2016/1/28.
 */
public class ReentrantLockTest {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        /**可以new出多个condition*/
        Condition readcondition = lock.newCondition();
        Condition writecondition = lock.newCondition();
        try {
            /**
             * 一般采用以下写法
             * while(!(ok to processed)){
             *     xxxx.await();
             * }
            * */
            readcondition.await();
            //writecondition.wait();
            lock.lock();

            /***
             * 解除其他线程的阻塞
             * */
            readcondition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }

    public static void reetrenLockTest(){

    }
}
