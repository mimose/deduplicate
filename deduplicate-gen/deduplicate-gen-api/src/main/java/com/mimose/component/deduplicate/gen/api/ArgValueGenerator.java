package com.mimose.component.deduplicate.gen.api;

/**
 * @author mimose
 * @description
 * @date 2021/3/25
 */
public interface ArgValueGenerator {

    /**
     * generate
     * @param args
     * @return
     */
    String generate(Object[] args);
}
