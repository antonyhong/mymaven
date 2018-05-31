package com.ryan.baidu.testandlearn.sys;

import java.util.Random;

public class RandomTest {
    public static void main(String[] args) {
        //1、 Math.random()
        //2、 Random random = new Random(); 基于seed的伪随机
        //3、 System.currentTimeMillis(); 取最后两位


        testRandomAPIs(new Random(1000), " 1st Random(1000)");
        testRandomAPIs(new Random(1000), " 2nd Random(1000)");
        // 新建“默认种子”的Random对象，并通过该种子去测试Random的API
        testRandomAPIs(new Random(), " 1st Random()");
        testRandomAPIs(new Random(), " 2nd Random()");
    }

    private static void randomWithTimeMills(){
        long l  = System.currentTimeMillis();
        // 通过l获取一个[0, 100)之间的整数
        final int i = (int)( l % 100 );
        System.out.printf("\n---- System.currentTimeMillis() ----\n l=%s i=%s\n", l, i);
    }

    private static  void randomWithMath(){
            // 底层使用Random的 nextDouble();
        double d =  Math.random();
    }

    private static void random(){
        Random random = new Random();

    }

    private static void testRandomAPIs(Random random, String title) {
        final int BUFFER_LEN = 5;

        // 获取随机的boolean值
        boolean b = random.nextBoolean();
        // 获取随机的数组buf[]
        byte[] buf = new byte[BUFFER_LEN];
        random.nextBytes(buf);
        // 获取随机的Double值，范围[0.0, 1.0)
        double d = random.nextDouble();
        // 获取随机的float值，范围[0.0, 1.0)
        float f = random.nextFloat();
        // 获取随机的int值
        int i1 = random.nextInt();
        // 获取随机的[0,100)之间的int值
        int i2 = random.nextInt(100);
        // 获取随机的高斯分布的double值
        double g = random.nextGaussian();
        // 获取随机的long值
        long l = random.nextLong();

        System.out.printf("\n---- %s ----\nb=%s, d=%s, f=%s, i1=%s, i2=%s, g=%s, l=%s, buf=[",
                title, b, d, f, i1, i2, g, l);
        for (byte bt:buf)
            System.out.printf("%s, ", bt);
        System.out.println("]");
    }
}
