package com.ming.testandlearn.concurrent.ratelimiter;

/**
 * Created by hongyongming on 2016/11/1.
 */
public interface IMultiRateLimiter {

    /**
     * ask for accessing resource .
     * if success then
     *       return true;
     * else
     *      return false;
     *the caller should handle this result;
    */
    boolean require(String resourceName);

    void release(String resourceName);
}
