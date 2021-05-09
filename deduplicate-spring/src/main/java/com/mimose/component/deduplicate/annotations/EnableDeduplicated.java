package com.mimose.component.deduplicate.annotations;

import com.mimose.component.deduplicate.spring.config.DeduplicateSpringConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author mimose
 * @description enable to open spring's deduplicated
 * @date 2021/4/30
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(DeduplicateSpringConfiguration.class)
public @interface EnableDeduplicated {
}
