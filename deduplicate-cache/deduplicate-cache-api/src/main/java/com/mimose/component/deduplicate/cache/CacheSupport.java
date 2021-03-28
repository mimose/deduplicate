package com.mimose.component.deduplicate.cache;

import com.mimose.component.deduplicate.cache.api.CacheManager;
import com.mimose.component.deduplicate.exceptions.ActionException;
import com.mimose.component.deduplicate.exceptions.LoaderException;
import com.mimose.component.deduplicate.utils.Assert;
import com.mimose.component.deduplicate.utils.Loader;
import org.apache.commons.lang3.StringUtils;

/**
 * @author mimose
 * @description provide Cache Support (Load specific cache manager)
 * @date 2021/3/25
 */
public final class CacheSupport {

    private static final CacheManager cacheManager;

    static {
        try {
            cacheManager = Loader.load(CacheManager.class, CacheSupport.class.getClassLoader());
        } catch (LoaderException e) {
            throw new ActionException("Can't support the Cache Manager, missing ServiceLoader");
        }
        Assert.notNull(cacheManager, "Can't support the Cache Manager, Load CacheManager result is null");
    }

    /**
     * cache the deduplicate key with ttl
     * @param deduplicateKey
     * @param ttl
     */
    public void put(String deduplicateKey, int ttl) {
        Assert.isTrue(ttl > 0, "Can't put the cache, ttl must be greater than zero");
        Assert.notBlank(deduplicateKey, "Can't put the cache, key cannot be empty");
        cacheManager.cache(deduplicateKey, ttl);
    }

    /**
     * check whether it is deduplicate
     * @param deduplicateKey
     * @return
     */
    public boolean checkDuplication(String deduplicateKey) {
        if(StringUtils.isEmpty(deduplicateKey)) {
            return false;
        }
        return cacheManager.check(deduplicateKey);
    }
}
