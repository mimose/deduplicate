package com.mimose.component.deduplicate.cache.redis.starter;

import com.mimose.component.deduplicate.cache.redis.RedisCacheManager;
import com.mimose.component.deduplicate.cache.redis.operators.LettuceRedisOperator;
import com.mimose.component.deduplicate.cache.redis.pool.LettuceRedisConnectionPool;
import com.mimose.component.deduplicate.log.FluentLogger;

/**
 * @author mimose
 * @description The Starter of RedisCache
 * @date 2021/5/15
 */
public final class RedisCacheStarter {
    private static FluentLogger LOGGER = FluentLogger.getLogger(RedisCacheStarter.class);
    private static final String MODULE = "RedisCache";

    public static void startRedisCache() {
        boolean automaticStart = RedisPropertiesHolder.checkIsAutomaticStart();
        if(automaticStart) {
            LettuceRedisOperator.withConnectionPool(
                    LettuceRedisConnectionPool.instance()
            );
            RedisCacheManager.withOperator(
                    new LettuceRedisOperator()
            );
            LOGGER.info().module(MODULE).message("start success").build();
        }
    }

}
