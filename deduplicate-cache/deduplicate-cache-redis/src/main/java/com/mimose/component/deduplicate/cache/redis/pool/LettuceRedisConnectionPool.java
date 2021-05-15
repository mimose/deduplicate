package com.mimose.component.deduplicate.cache.redis.pool;

import com.mimose.component.deduplicate.cache.redis.starter.RedisPropertiesHolder;
import com.mimose.component.deduplicate.utils.Assert;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.support.ConnectionPoolSupport;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import java.text.MessageFormat;
import java.time.Duration;
import java.util.Map;
import java.util.Objects;

import static com.mimose.component.deduplicate.cache.redis.starter.RedisProperties.RedisPropertiesKey.*;

/**
 * @author mimose
 * @description provide Redis Connection Pool
 * @date 2021/5/15
 */
public final class LettuceRedisConnectionPool {

    private LettuceRedisConnectionPool() {}

    /**
     * get connection from pool
     * @return
     * @throws Exception
     */
    public StatefulRedisConnection getConnection() throws Exception {
        return connectionPool.borrowObject(BORROW_MAX_WAIT_MILLIS);
    }

    /**
     * return connection to pool
     * @param connection
     */
    public void returnConnection(StatefulRedisConnection connection) {
        if(Objects.nonNull(connection)) {
            connectionPool.returnObject(connection);
        }
    }

    /**
     * get the output statistics str
     * @return
     */
    public String statistics() {
        return MessageFormat.format(STATISTIC_OUTPUT,
                connectionPool.getMinIdle(),
                connectionPool.getMaxIdle(),
                connectionPool.getMaxTotal(),
                connectionPool.getNumIdle(),
                connectionPool.getNumActive(),
                connectionPool.getNumWaiters()
        );
    }

    // ========= instance

    private static final String STATISTIC_OUTPUT = "The RedisConnectionPool statistics >>> [MinIdle: {0}, MaxIdle: {1}, MaxTotal: {2}, NumIdle: {3}, NumActive: {4}, NumWaiters: {5}]";

    private static final long BORROW_MAX_WAIT_MILLIS = 10000L;

    private static Map<String, String> PROPERTIES;

    private static LettuceRedisConnectionPool POOL_INSTANCE;

    private RedisClient redisClient;

    private GenericObjectPoolConfig poolConfig;

    private GenericObjectPool<StatefulRedisConnection<String, String>> connectionPool;

    public static LettuceRedisConnectionPool instance() {
        if(Objects.isNull(POOL_INSTANCE)) {
            synchronized (LettuceRedisConnectionPool.class) {
                if(Objects.isNull(POOL_INSTANCE)) {
                    PROPERTIES = RedisPropertiesHolder.getProperties();
                    POOL_INSTANCE = new LettuceRedisConnectionPool();
                    POOL_INSTANCE.initRedisClient();
                    POOL_INSTANCE.initConnectionPoolConfig();
                    POOL_INSTANCE.initConnectionPool();
                }
            }
        }
        return POOL_INSTANCE;
    }

    /**
     * when get instance. do the RedisClient init
     */
    private void initRedisClient() {
        Assert.notNull(getRedisClient(), "initialize Redis Client fail");
    }

    /**
     * when get instance. do the Connection Pool Config init
     */
    private void initConnectionPoolConfig() {
        Assert.notNull(getPoolConfig(), "initialize Redis Connection Pool Config fail");
    }

    /**
     * when get instance. do the Connection Pool init
     */
    private void initConnectionPool() {
        connectionPool = ConnectionPoolSupport.createGenericObjectPool(() -> getRedisClient().connect(), getPoolConfig());
    }

    /**
     * Provide the Redis Client for the connection pool
     * @return
     */
    private RedisClient getRedisClient() {
        if(Objects.isNull(redisClient)) {
            synchronized (LettuceRedisConnectionPool.class) {
                if(Objects.isNull(redisClient)) {
                    final RedisURI redisURI = RedisURI.builder()
                            .withHost(PROPERTIES.get(HOST))
                            .withPort(Integer.valueOf(PROPERTIES.get(PORT)))
                            .withPassword(PROPERTIES.get(PASSWORD).toCharArray())
                            .withDatabase(Integer.valueOf(PROPERTIES.get(DATABASE)))
                            .withTimeout(Duration.ofSeconds(Integer.valueOf(PROPERTIES.get(TIMEOUT))))
                            .build();

                    redisClient = RedisClient.create(redisURI);
                }
            }
        }
        return redisClient;
    }

    /**
     * Provide the Connection Pool Config for the connection pool
     * @return
     */
    public GenericObjectPoolConfig getPoolConfig() {
        if(Objects.isNull(poolConfig)) {
            synchronized (LettuceRedisConnectionPool.class) {
                if(Objects.isNull(poolConfig)) {
                    poolConfig = new GenericObjectPoolConfig();
                    poolConfig.setMinIdle(Integer.valueOf(PROPERTIES.get(MIN_IDLE)));
                    poolConfig.setMaxIdle(Integer.valueOf(PROPERTIES.get(MAX_IDLE)));
                }
            }
        }
        return poolConfig;
    }
}
