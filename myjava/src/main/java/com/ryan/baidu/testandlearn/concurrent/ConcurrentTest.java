package com.ryan.baidu.testandlearn.concurrent;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;

/**
 * Created by ming on 2016/1/19.
 */
public class ConcurrentTest {

    /** 使用 Concurrent Collection */
    private static final ConcurrentMap<String, String> map = new ConcurrentHashMap<String, String>();

    public static String intern(String s) {

        String previousValue = map.putIfAbsent(s, s);
        return previousValue == null ? s : previousValue;
    }


    /**
     * 使用 CountDownLatch 替换直接使用wait notify
     * <p>
     * 例子中的三个 CountDownLatch 可以用 CyclicBarrier 来代替.
     */

    public static long time(Executor executor, int concurrency, final Runnable action) throws InterruptedException {
        final CountDownLatch ready = new CountDownLatch(concurrency);
        final CountDownLatch start = new CountDownLatch(1);
        final CountDownLatch done = new CountDownLatch(concurrency);

        for (int i = 0; i < concurrency; i++) {
            executor.execute(() -> {
                ready.countDown();
                try {
                    start.await();
                    action.run();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    done.countDown();
                }
            });
        }

        /**ready.wait(); 用wait()会报错

         * */

        ready.await();
        /**对于间歇式的计时 System.nanoTime 比 System.currentTimeMill 更精确,因为它不受系统实时时钟的调整所影响*/
        long startNanos = System.nanoTime();
        start.countDown();
        done.wait();
        return System.nanoTime() - startNanos;

    }


}
