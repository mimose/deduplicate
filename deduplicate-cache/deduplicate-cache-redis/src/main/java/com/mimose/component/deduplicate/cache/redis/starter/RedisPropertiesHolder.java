package com.mimose.component.deduplicate.cache.redis.starter;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import static com.mimose.component.deduplicate.cache.redis.starter.RedisProperties.RedisPropertiesKey.*;
import static com.mimose.component.deduplicate.cache.redis.starter.RedisProperties.RedisPropertiesKey.MAX_IDLE;
import static com.mimose.component.deduplicate.cache.redis.starter.RedisProperties.RedisPropertiesValue.*;

/**
 * @author mimose
 * @description Redis Properties Holder
 * @date 2021/5/15
 */
public final class RedisPropertiesHolder {

    private static Map<String, String> PROPERTIES;

    public static Map<String, String> getProperties() {
        if(Objects.isNull(PROPERTIES)) {
            synchronized (RedisPropertiesHolder.class) {
                if(Objects.isNull(PROPERTIES)) {
                    PROPERTIES = new ConcurrentHashMap<>(8);

                    PROPERTIES.put(HOST, System.getProperty(HOST, HOST_DEFAULT));
                    PROPERTIES.put(PORT, System.getProperty(PORT, PORT_DEFAULT));
                    PROPERTIES.put(PASSWORD, System.getProperty(PASSWORD, PASSWORD_DEFAULT));
                    PROPERTIES.put(DATABASE, System.getProperty(DATABASE, DATABASE_DEFAULT));
                    PROPERTIES.put(TIMEOUT, System.getProperty(TIMEOUT, TIMEOUT_DEFAULT));
                    PROPERTIES.put(MIN_IDLE, System.getProperty(MIN_IDLE, MIN_IDLE_DEFAULT));
                    PROPERTIES.put(MAX_IDLE, System.getProperty(MAX_IDLE, MAX_IDLE_DEFAULT));
                }
            }
        }
        return PROPERTIES;
    }

    /**
     * Whether to automatically perform Redis Starter
     * @return
     */
    public static boolean checkIsAutomaticStart() {
        return Boolean.valueOf(System.getProperty(AUTOMATIC_START, "true"));
    }
}
