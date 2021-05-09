package com.mimose.component.deduplicate.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 不需要手动引入组件，组件会自动注入
 * en: No need to manually import components, components will be injected automatically
 * @author mimose
 * @description
 * @date 2021/5/9
 */
@SpringBootApplication
@ComponentScan("com.mimose.component.deduplicate.*")
public class SpringStarterSampleStarter {

    public static void main(String... args) {
        SpringApplication.run(SpringStarterSampleStarter.class, args);
    }
}
