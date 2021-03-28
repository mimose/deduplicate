package com.mimose.component.deduplicate.cache.api;

/**
 * @author mimose
 * @description Cache manager api
 * @date 2021/3/25
 */
public interface CacheManager {

    /**
     * cache the deduplicate key with ttl
     * @param deduplicateKey
     * @param ttl               Second
     * @return
     */
    void cache(final String deduplicateKey, final int ttl);

    /**
     * check whether it is deduplicate
     * @param deduplicateKey
     * @return
     */
    boolean check(final String deduplicateKey);
}
