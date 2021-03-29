package com.mimose.component.deduplicate.cache.map;

import com.mimose.component.deduplicate.annotations.Level;
import com.mimose.component.deduplicate.cache.api.CacheManager;
import com.mimose.component.deduplicate.exceptions.CacheException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author mimose
 * @description provide specific cache manager (Map)
 * @date 2021/3/25
 */
@Level(priority = 1)
public class MapCacheManager implements CacheManager {
    private static final Map<String, Long> CACHE_MAP = new ConcurrentHashMap<>();

    @Override
    public void cache(String deduplicateKey, int ttl) {
        if(ttl <= 0) {
            throw new CacheException("ttl shout be greater than 0");
        }
        CACHE_MAP.putIfAbsent(deduplicateKey, System.currentTimeMillis() + ttl * 1000);
    }

    @Override
    public boolean check(String deduplicateKey) {
        return CACHE_MAP.containsKey(deduplicateKey);
    }

    static {
        (new ScheduledThreadPoolExecutor(1, (runnable) -> new Thread(runnable, "MAP_CACHE_EVICT_TASK"))).scheduleAtFixedRate(() -> {
            final long current = System.currentTimeMillis();
            CACHE_MAP.entrySet().removeIf(entry -> entry.getValue() <= current);
        }, 500, 500, TimeUnit.MILLISECONDS);
    }
}
