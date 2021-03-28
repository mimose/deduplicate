package com.mimose.component.deduplicate.annotations;

import java.lang.annotation.*;

/**
 * @author mimose
 * @description used to judge the priority when loading the objects
 * @date 2021/3/25
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Level {
    Integer DEFAULT_PRIORITY = 0;

    /**
     * the priority value.
     * the default is 0, which means the lowest
     * @return
     */
    int priority() default 0;
}
