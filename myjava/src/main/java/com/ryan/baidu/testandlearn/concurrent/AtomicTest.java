package com.ryan.baidu.testandlearn.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by hongyongming on 2016/9/26.
 */
public class AtomicTest {
    public static void main(String[] args) {
        Integer in = new Integer(10);
        Integer in1 = Integer.valueOf(10);
        Integer in3 =10;

        System.out.println(in == in1 );
        System.out.println(in == in3 );


    }

    private void test(){

        AtomicInteger atomicInteger = new AtomicInteger();

        //CAS
        atomicInteger.incrementAndGet();
        System.out.println(atomicInteger.intValue());


    }
}
