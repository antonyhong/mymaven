package com.ming.testandlearn.concurrent.ratelimiter;

import com.google.common.collect.Maps;
import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by hongyongming on 2016/11/1.
 */
public class MultiResourceRateLimitSample implements IMultiRateLimiter {

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

    public static class RateLimitException extends Exception {

        private static final long serialVersionUID = 1L;

        private String resource;

        public String getResource() {
            return resource;
        }

        public RateLimitException(String resource) {
            super(resource + " should not be visited so frequently");
            this.resource = resource;
        }

        @Override
        public synchronized Throwable fillInStackTrace() {
            return this;
        }
    }

    private static final ConcurrentMap<String, RateLimiter> resourceLimiterMap = new ConcurrentHashMap<>();


    public static void updateResourceQps(String resource, double qps) {
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

    public static void removeResource(String resource) {
        resourceLimiterMap.remove(resource);
    }


}
