package com.mimose.component.deduplicate.annotations;

import com.mimose.component.deduplicate.constants.Constant;

import java.lang.annotation.*;

/**
 * @author mimose
 * @description
 * @date 2021/3/26
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Deduplicated {

    int ttl() default 1;

    String tokenGAid() default Constant.DEDUPLICATE_TOKEN_HEADER;

    TokenGType tokenGType() default TokenGType.HEADER;

    enum TokenGType {
        /**
         * How to pass token: request header
         */
        HEADER
    }
}
