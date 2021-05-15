package com.mimose.component.deduplicate.cache.redis;

import com.mimose.component.deduplicate.annotations.Level;
import com.mimose.component.deduplicate.cache.api.CacheManager;
import com.mimose.component.deduplicate.cache.redis.operators.api.RedisOperator;
import com.mimose.component.deduplicate.exceptions.CacheException;

/**
 * @author mimose
 * @description provide specific cache manager (Redis)
 * @date 2021/5/15
 */
@Level(priority = 2)
public class RedisCacheManager implements CacheManager {

    private static RedisOperator redisOperator;

    @Override
    public void cache(String deduplicateKey, int ttl) {
        if(ttl <= 0) {
            throw new CacheException("ttl shout be greater than 0");
        }
        redisOperator.setIfAbsent(deduplicateKey, "1", ttl);
    }

    @Override
    public boolean check(String deduplicateKey) {
        return redisOperator.exists(deduplicateKey);
    }

    public static void withOperator(RedisOperator redisOperator) {
        RedisCacheManager.redisOperator = redisOperator;
    }
}
