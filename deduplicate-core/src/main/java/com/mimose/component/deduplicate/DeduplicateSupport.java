package com.mimose.component.deduplicate;

import com.mimose.component.deduplicate.cache.CacheSupport;
import com.mimose.component.deduplicate.exceptions.ActionException;
import com.mimose.component.deduplicate.exceptions.DuplicateException;
import com.mimose.component.deduplicate.gen.ArgValueSupport;
import com.mimose.component.deduplicate.instances.Instance;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author mimose
 * @description
 * @date 2021/3/26
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DeduplicateSupport {

    private static ArgValueSupport ARG_VALUE_SUPPORT;

    private static CacheSupport CACHE_SUPPORT;

    static InnerDeduplicateWorker newInstance() {
        return new InnerDeduplicateWorker();
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    static class InnerDeduplicateWorker {

        private Method method;

        private Object[] params;

        private int ttl;

        public InnerDeduplicateWorker method(final Method method) {
            this.method = method;
            return this;
        }

        public InnerDeduplicateWorker params(final Object[] params) {
            this.params = params;
            return this;
        }

        public InnerDeduplicateWorker ttl(final int ttl) {
            this.ttl = ttl;
            return this;
        }

        public void work() throws DuplicateException {
            final String argValueKey = argValue().genArgValueKey(this.method, this.params);
            String fullKey = argValueKey;
            final boolean isDuplicate = cache().checkDuplication(fullKey);
            if(isDuplicate) {
                throw new DuplicateException();
            }

            cache().put(fullKey, this.ttl);

        }
    }


    private static ArgValueSupport argValue() {
        if(Objects.isNull(ARG_VALUE_SUPPORT)) {
            synchronized (Deduplicate.class) {
                if(Objects.isNull(ARG_VALUE_SUPPORT)) {
                    ARG_VALUE_SUPPORT = Instance.singleton(ArgValueSupport.class);
                }
            }
        }
        if(Objects.isNull(ARG_VALUE_SUPPORT)) {
            throw new ActionException("ArgValueSupport is null");
        }
        return ARG_VALUE_SUPPORT;
    }

    private static CacheSupport cache() {
        if(Objects.isNull(CACHE_SUPPORT)) {
            synchronized (Deduplicate.class) {
                if(Objects.isNull(CACHE_SUPPORT)) {
                    CACHE_SUPPORT = Instance.singleton(CacheSupport.class);
                }
            }
        }
        if(Objects.isNull(CACHE_SUPPORT)) {
            throw new ActionException("CacheSupport is null");
        }
        return CACHE_SUPPORT;
    }
}
