package com.mimose.component.deduplicate.cache.redis.starter;

/**
 * @author mimose
 * @description
 * @date 2021/5/15
 */
public class RedisProperties {

    public static class RedisPropertiesKey {

        public static final String AUTOMATIC_START = "deduplicate.cache.redis.automatic-start";

        public static final String MODE = "deduplicate.cache.redis.mode";

        public static final String HOST = "deduplicate.cache.redis.host";

        public static final String PORT = "deduplicate.cache.redis.port";

        public static final String PASSWORD = "deduplicate.cache.redis.password";

        public static final String DATABASE = "deduplicate.cache.redis.database";

        public static final String TIMEOUT = "deduplicate.cache.redis.timeout";

        public static final String MIN_IDLE = "deduplicate.cache.redis.minIdle";

        public static final String MAX_IDLE = "deduplicate.cache.redis.maxIdle";
    }

    public static class RedisPropertiesValue {

        public static final String HOST_DEFAULT = "localhost";

        public static final String PORT_DEFAULT = "6379";

        public static final String PASSWORD_DEFAULT = "123456";

        public static final String DATABASE_DEFAULT = "1";

        public static final String TIMEOUT_DEFAULT = "10";

        public static final String MIN_IDLE_DEFAULT = "1";

        public static final String MAX_IDLE_DEFAULT = "10";
    }

}
