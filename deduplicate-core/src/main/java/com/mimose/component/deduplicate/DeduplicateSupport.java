package com.mimose.component.deduplicate;

import com.mimose.component.deduplicate.annotations.Deduplicated;
import com.mimose.component.deduplicate.cache.CacheSupport;
import com.mimose.component.deduplicate.exceptions.ActionException;
import com.mimose.component.deduplicate.exceptions.DuplicateException;
import com.mimose.component.deduplicate.gen.GenerateSupport;
import com.mimose.component.deduplicate.instances.Instance;
import com.mimose.component.deduplicate.log.FluentLogger;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author mimose
 * @description support to do duplicate check
 * @date 2021/3/26
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DeduplicateSupport {
    private static final FluentLogger LOGGER = FluentLogger.getLogger(DeduplicateSupport.class);
    private static final String MODULE = "DEDUPLICATE_SUPPORT";

    private static GenerateSupport GENERATE_SUPPORT;

    private static CacheSupport CACHE_SUPPORT;

    static InnerDeduplicateWorker newInstance() {
        return new InnerDeduplicateWorker();
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    static class InnerDeduplicateWorker {

        private Method method;

        private Object[] params;

        private int ttl;

        private String tokenGAid;

        private Deduplicated.TokenGType tokenGType;

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

        public InnerDeduplicateWorker tokenGAid(final String tokenGAid) {
            this.tokenGAid = tokenGAid;
            return this;
        }

        public InnerDeduplicateWorker tokenGType(final Deduplicated.TokenGType tokenGType) {
            this.tokenGType = tokenGType;
            return this;
        }

        public void work() throws DuplicateException {
            final String argValueKey = gen().genArgValueKey(this.method, this.params);
            // TODO token
            final String token = gen().genToken(this.tokenGAid, this.tokenGType, this.params);
            String fullKey = argValueKey;
            final boolean isDuplicate = cache().checkDuplication(fullKey);
            if(isDuplicate) {
                LOGGER.warn().module(MODULE).message("Duplicate attack happened!! Method: [{} # {}]").args(this.method.getDeclaringClass().getName(), this.method.getName()).build();
                throw new DuplicateException();
            }

            cache().put(fullKey, this.ttl);
        }
    }


    private static GenerateSupport gen() {
        if(Objects.isNull(GENERATE_SUPPORT)) {
            synchronized (Deduplicate.class) {
                if(Objects.isNull(GENERATE_SUPPORT)) {
                    GENERATE_SUPPORT = Instance.singleton(GenerateSupport.class);
                }
            }
        }
        if(Objects.isNull(GENERATE_SUPPORT)) {
            LOGGER.error().module(MODULE).message("ARG_VALUE_SUPPORT is not present, please determine whether there is a dependency").build();
            throw new ActionException("ArgValueSupport is null");
        }
        return GENERATE_SUPPORT;
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
            LOGGER.error().module(MODULE).message("CACHE_SUPPORT is not present, please determine whether there is a dependency").build();
            throw new ActionException("CacheSupport is null");
        }
        return CACHE_SUPPORT;
    }
}
