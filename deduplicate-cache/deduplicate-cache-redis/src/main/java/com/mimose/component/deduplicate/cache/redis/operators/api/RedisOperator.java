package com.mimose.component.deduplicate.cache.redis.operators.api;

/**
 * @author mimose
 * @description Redis client operator api
 * @date 2021/5/15
 */
public interface RedisOperator {

    /**
     * set If Absent
     * @param key
     * @param value
     * @param ttl
     * @return
     */
    boolean setIfAbsent(String key, String value, int ttl);

    /**
     * check if exists
     * @param key
     * @return
     */
    boolean exists(String key);
}
