package com.mimose.component.deduplicate.annotations;

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

    String tokenGAid() default "";

    TokenGType tokenGType() default TokenGType.HEADER;

    enum TokenGType {
        HEADER
    }
}
