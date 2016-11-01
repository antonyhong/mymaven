package com.ming.testandlearn.concurrent.ratelimiter;

import com.google.common.util.concurrent.RateLimiter;

/**
 * Created by HONGYONGMING on 2016/10/24.
 */
public class RateLimiterTest {

    public static void main(String[] args) {
        RateLimiter rateLimiter = RateLimiter .create(200);

        //阻塞方法
        rateLimiter.acquire();

        //非阻塞方法
        rateLimiter.tryAcquire();
    }
}
