package com.mimose.component.deduplicate.cache.redis.operators;

import com.mimose.component.deduplicate.cache.redis.operators.api.RedisOperator;
import com.mimose.component.deduplicate.cache.redis.pool.LettuceRedisConnectionPool;
import com.mimose.component.deduplicate.cache.redis.starter.RedisProperties;
import com.mimose.component.deduplicate.log.FluentLogger;
import io.lettuce.core.SetArgs;
import io.lettuce.core.api.StatefulRedisConnection;

import java.time.Duration;

/**
 * @author mimose
 * @description Redis client operator
 * @date 2021/5/15
 */
public class LettuceRedisOperator implements RedisOperator {
    private static final FluentLogger LOGGER = FluentLogger.getLogger(LettuceRedisOperator.class);

    private static LettuceRedisConnectionPool connectionPool;

    public static void withConnectionPool(LettuceRedisConnectionPool connectionPool) {
        LettuceRedisOperator.connectionPool = connectionPool;
    }

    @Override
    public boolean setIfAbsent(String key, String value, int ttl) {
        StatefulRedisConnection connection = null;
        try {
            connection = connectionPool.getConnection();
            connection.sync().set(key, value, SetArgs.Builder.nx().ex(Duration.ofSeconds(ttl)));
            return true;
        } catch (Exception e) {
            LOGGER.error().module(RedisProperties.MODULE).message("set If Absent fail, key: {}, value: {}, ttl: {}").args(key, value, ttl).throwable(e).build();
            return false;
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public boolean exists(String key) {
        StatefulRedisConnection connection = null;
        try {
            connection = connectionPool.getConnection();
            return connection.sync().exists(key) > 0;
        } catch (Exception e) {
            LOGGER.error().module(RedisProperties.MODULE).message("check If exists fail, key: {}").args(key).throwable(e).build();
        } finally {
            connectionPool.returnConnection(connection);
        }
        return true;
    }
}
