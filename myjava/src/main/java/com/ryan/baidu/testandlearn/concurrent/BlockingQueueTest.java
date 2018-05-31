package com.ryan.baidu.testandlearn.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by hongyongming on 2016/1/28.
 */
public class BlockingQueueTest {
    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(45);
        /**阻塞队列，满时的添加操作，空时的读取操作，都会阻塞???????*/
        blockingQueue.add("");
        blockingQueue.poll();

    }
}
