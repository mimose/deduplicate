package com.mimose.component.deduplicate.gen;

import com.mimose.component.deduplicate.DeduplicateSupport;
import com.mimose.component.deduplicate.annotations.Deduplicated;
import com.mimose.component.deduplicate.exceptions.ActionException;
import com.mimose.component.deduplicate.gen.api.ArgValueGenerator;
import com.mimose.component.deduplicate.gen.api.Generator;
import com.mimose.component.deduplicate.gen.api.HttpRequestTokenGenerator;
import com.mimose.component.deduplicate.instances.Instance;
import com.mimose.component.deduplicate.log.FluentLogger;
import com.mimose.component.deduplicate.utils.ObjectUtil;

/**
 * @author mimose
 * @description
 * @date 2021/4/18
 */
public final class GenerateFactory {
    private static final FluentLogger LOGGER = FluentLogger.getLogger(DeduplicateSupport.class);
    private static final String MODULE = "GENERATE_FACTORY";

    public static GenerateSupport Support() {
        return ObjectUtil.requireNonNull(Instance.singleton(GenerateSupport.class), () -> {
            LOGGER.error().module(MODULE).message("ARG_VALUE_SUPPORT is not present").build();
            return new ActionException.NormalActionException("ArgValueSupport is null");
        });
    }

    public static ArgValueGenerateProducts ArgValue() {
        return ObjectUtil.requireNonNull(Instance.singleton(ArgValueGenerateProducts.class), () -> {
            LOGGER.error().module(MODULE).message("ArgValueGenerateProducts is not present").build();
            return new ActionException.NormalActionException("ArgValueGenerateProducts is null");
        });
    }

    public static TokenGenerateProducts Token() {
        return ObjectUtil.requireNonNull(Instance.singleton(TokenGenerateProducts.class), () -> {
            LOGGER.error().module(MODULE).message("TokenGenerateProducts is not present").build();
            return new ActionException.NormalActionException("TokenGenerateProducts is null");
        });
    }

    public static class ArgValueGenerateProducts {

        public Generator singleton() {
            return ObjectUtil.requireNonNull(Instance.singleton(ArgValueGenerator.class), () -> {
                LOGGER.error().module(MODULE).message("ArgValueGenerator is not present").build();
                return new ActionException.NormalActionException("ArgValueGenerator is null");
            });
        }
    }

    public static class TokenGenerateProducts {

        public Generator by(Deduplicated.TokenGType tokenGType) {
            return ObjectUtil.requireNonNull(() -> {
                switch (tokenGType) {
                    case HEADER:
                        return Instance.singleton(HttpRequestTokenGenerator.class);
                    default:
                        throw new ActionException.UnsupportedActionException("this tokenGType (" + tokenGType + ") is not supported");
            }}, () -> {
                LOGGER.error().module(MODULE).message("TokenGenerator is not present").build();
                return new ActionException.NormalActionException("TokenGenerator is null");
            });

        }
    }
}
