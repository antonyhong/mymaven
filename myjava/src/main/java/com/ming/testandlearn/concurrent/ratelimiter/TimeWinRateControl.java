package com.ming.testandlearn.concurrent.ratelimiter;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by hongyongming on 2016/11/3.
 */
public class TimeWinRateControl implements IMultiRateLimiter {

    private static final ConcurrentMap<String, CounterCache> resourceLimiterMap = new ConcurrentHashMap<>();

    @Override
    public void addOrUpdateResourceQps(String resource, long qps) {
        CounterCache counterCache = resourceLimiterMap.get(resource);
        if (counterCache == null) {
            counterCache = new CounterCache(qps);
            CounterCache putByOtherThread = resourceLimiterMap.putIfAbsent(resource, counterCache);
            if (putByOtherThread != null) {
                counterCache = putByOtherThread;
            }
        }
        counterCache.setQps(qps);
    }

    @Override
    public void removeResource(String resource) {
        resourceLimiterMap.remove(resource);
    }

    @Override
    public boolean require(String resourceName) {
        CounterCache counterCache = resourceLimiterMap.get(resourceName);
        if (counterCache == null) {
            return true;
        }
        return counterCache.require();
    }

    @Override
    public void release(String resourceName) {

    }

    private static class CounterCache {
        private long qps;
        LoadingCache<Long, AtomicLong> counter;

        public CounterCache(long qps) {
            this.qps = qps;
            counter = CacheBuilder.newBuilder()
                    .expireAfterWrite(2, TimeUnit.SECONDS)// 时间窗口为 1s ，默认2秒过期 保证1秒内的计数器是有的
                    .build(new CacheLoader<Long, AtomicLong>() {
                        @Override
                        public AtomicLong load(Long seconds) throws Exception {
                            return new AtomicLong(0);
                        }
                    });
        }

        public boolean require() {
            long currentSeconds = System.currentTimeMillis() / 1000;
            AtomicLong curCount = null;
            try {
                curCount = counter.get(currentSeconds);//没有该key，会自动添加
            } catch (ExecutionException e) {
                //单出现异常时，限流失效
                curCount = new AtomicLong(0);
            }
            if (curCount.incrementAndGet() > qps) {
                return false;
            } else {
                return true;
            }
        }
        public long getQps() {
            return qps;
        }

        public void setQps(long qps) {
            this.qps = qps;
        }
    }


    public static void main(String[] args) throws InterruptedException {
        TimeWinRateControl limiter = new TimeWinRateControl();
        limiter.addOrUpdateResourceQps("url1", 10);
        limiter.addOrUpdateResourceQps("url2", 10);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 12; j++) {
                if (limiter.require("url1")) {
                    System.out.println(String.format("【%s】访问成功", i + "," + j));
                } else {
                    System.out.println(String.format("【%s】访问量过高，你被限流了", i + "," + j));
                }
            }
            Thread.sleep(900);
        }
    }

}
