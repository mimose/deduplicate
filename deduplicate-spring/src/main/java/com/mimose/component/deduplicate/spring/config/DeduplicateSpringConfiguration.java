package com.mimose.component.deduplicate.spring.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author mimose
 * @description
 * @date 2021/4/30
 */
@Configurable
@ComponentScan("com.mimose.component.deduplicate.spring")
@EnableAspectJAutoProxy
public class DeduplicateSpringConfiguration {
}
