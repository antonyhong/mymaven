package com.ming.testandlearn.concurrent.ratelimiter;

/**
 * Created by hongyongming on 2016/11/1.
 */
public interface IMultiRateLimiter {

    void addOrUpdateResourceQps(String resource, long qps);

    void  removeResource(String resource);

    boolean require(String resourceName);

    void release(String resourceName);
}
