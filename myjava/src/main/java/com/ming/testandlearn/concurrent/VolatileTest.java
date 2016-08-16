package com.ming.testandlearn.concurrent;

/**
 * Created by hongyongming on 2016/8/5.
 * 名词解释
 * 共享变量:
 * 在多个线程之间能够被共享的变量被称为共享变量。共享变量包括所有的实例变量，静态变量和数组元素。他们都被存放在堆内存中，Volatile只作用于共享变量。
 * <p>
 * 在写操作中,使用volatile 变量会被直接写回内存(保证处理完成)
 * 读的操作中,也会直接读取内存的值。
 * <p>
 * Volatile 变量具有 synchronized 的可见性特性，但是不具备原子特性。这就是说线程能够自动发现 volatile 变量的最新值
 */
public class VolatileTest {
    private volatile int value;
    private volatile int lockB = 1;
    private  int lockA = 1;

    public static void main(String[] args) throws InterruptedException {
        new VolatileTest().threadVisibleTest();


    }

    /**
     *  理论上,由于线程的可见性
     *     tA 由于不能及时读到 lockA 的变化 会发生死循环
     *     tB 由于 lockB volatile修饰的具有可见性可以正常结束
     *   然而实验并没有验证上诉结论,可能是JDK进行优化了。
     *
     * */
    public void threadVisibleTest() throws InterruptedException {
        Thread tA = new Thread(new Runnable() {
            @Override
            public void run() {
                while (lockA > 0) {

                    System.out.println(Thread.currentThread().getId() + ":lockA = " + lockA);
                }
            }
        });

        Thread tB = new Thread(new Runnable() {
            @Override
            public void run() {
                while (lockB > 0) {
                    System.out.println(Thread.currentThread().getId() + ":lockB = " + lockB);
                }
            }
        });


        tA.start();
        tB.start();
        Thread.sleep(100);
        lockA = 0;
        lockB = 0;

    }

    public static void t1() {

        for (int i = 0; i < 1000; i++) {

        }
    }

    public static void t2() {

        for (int i = 0; i < 1000; i++) {

        }
    }


}
