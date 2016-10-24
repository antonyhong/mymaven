package com.ming.testandlearn.concurrent.guava;

import com.google.common.util.concurrent.RateLimiter;

/**
 * Created by HONGYONGMING on 2016/10/24.
 */
public class RateLimiterTest {

    public static void main(String[] args) {
        RateLimiter rateLimiter = RateLimiter .create(200);

        //rateLimiter.acquire()
    }
}
