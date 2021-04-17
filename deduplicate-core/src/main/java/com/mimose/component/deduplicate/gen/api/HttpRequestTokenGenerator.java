package com.mimose.component.deduplicate.gen.api;

/**
 * @author mimose
 * @description provide specific HttpRequestTokenGenerator
 * @date 2021/4/17
 */
public class HttpRequestTokenGenerator implements Generator {

    @Override
    public String generate(Object[] args) {
        if(args == null) {
            return "";
        }
        return null;
    }
}
