package com.ryan.baidu.testandlearn.concurrent.ratelimiter;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by hongyongming on 2016/11/1.
 */
public class MultiResourceRateLimiter implements IMultiRateLimiter {
    private static final ConcurrentMap<String, RateLimiter> resourceLimiterMap = new ConcurrentHashMap<>();

    @Override
    public boolean require(String resourceName) {
        RateLimiter limiter = resourceLimiterMap.get(resourceName);
        if (limiter == null) {
            // no limit ;
            return true;
        }
        return limiter.tryAcquire();
    }

    @Override
    public void release(String resourceName) {
        //do nothing when use RateLimiter
    }

//    public static class RateLimitException extends Exception {
//
//        private static final long serialVersionUID = 1L;
//
//        private String resource;
//
//        public String getResource() {
//            return resource;
//        }
//
//        public RateLimitException(String resource) {
//            super(resource + " should not be visited so frequently");
//            this.resource = resource;
//        }
//
//        @Override
//        public synchronized Throwable fillInStackTrace() {
//            return this;
//        }
//    }


    public void addOrUpdateResourceQps(String resource, long qps) {
        RateLimiter limiter = resourceLimiterMap.get(resource);
        if (limiter == null) {
            limiter = RateLimiter.create(qps);
            RateLimiter putByOtherThread = resourceLimiterMap.putIfAbsent(resource, limiter);
            if (putByOtherThread != null) {
                limiter = putByOtherThread;
            }
        }
        limiter.setRate(qps);
    }

    public void removeResource(String resource) {
        resourceLimiterMap.remove(resource);
    }


    public static void main(String[] args) throws InterruptedException {
        MultiResourceRateLimiter limiter = new MultiResourceRateLimiter();
        limiter.addOrUpdateResourceQps("url1", 20);
        limiter.addOrUpdateResourceQps("url2", 10);


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                if(limiter.require("url1")){
                    System.out.println(String.format("【%s】访问成功",i+","+j));
                }else
                {
                    System.out.println(String.format("【%s】访问量过高，你被限流了",i+","+j));
                }
            }
            Thread.sleep(200);
        }
    }

}
