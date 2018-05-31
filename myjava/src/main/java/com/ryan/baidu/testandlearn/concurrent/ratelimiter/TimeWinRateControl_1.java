package com.ryan.baidu.testandlearn.concurrent.ratelimiter;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by hongyongming on 2016/11/3.
 */
public class TimeWinRateControl_1 {
    public static void main(String[] args) throws ExecutionException {
        //使用 google 的guava库中的loadingCache
        LoadingCache<Long, AtomicLong> counter = CacheBuilder.newBuilder()
                .expireAfterWrite(2, TimeUnit.SECONDS)// 时间窗口为 1s ，默认2秒过期 保证1秒内的计数器是有的
                .build(new CacheLoader<Long, AtomicLong>() {
                    @Override
                    public AtomicLong load(Long seconds) throws Exception {
                        return new AtomicLong(0);
                    }
                });

        //并发测试：
        long limit = 10;
        for (int i = 0; i < 20; i++) {
            long currentSeconds = System.currentTimeMillis() / 1000;
            if (counter.get(currentSeconds).incrementAndGet() > limit) {//counter.get 会自动添加未存在的key
                System.out.println("限流了:" + currentSeconds);
            } else {
                System.out.println("通过了" + currentSeconds);
            }
        }
    }
}
