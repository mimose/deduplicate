package com.mimose.component.deduplicate.sample;

import com.mimose.component.deduplicate.annotations.EnableDeduplicated;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 需要通过注解手动引入组件，不需要使用的时，移除即可
 * en: need to manually introduce components through annotations. When you don’t need to use them, just remove them.
 * @author mimose
 * @description
 * @date 2021/5/9
 */
@SpringBootApplication
@ComponentScan("com.mimose.component.deduplicate.*")
/**
 * 需要使用该注解来引入组件
 * en: Need to use this annotation to introduce components
 */
@EnableDeduplicated
public class SpringSampleStarter {

    public static void main(String... args) {
        SpringApplication.run(SpringSampleStarter.class, args);
    }
}
