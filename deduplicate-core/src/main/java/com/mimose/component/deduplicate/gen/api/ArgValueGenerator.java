package com.mimose.component.deduplicate.gen.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mimose.component.deduplicate.exceptions.GenException;

/**
 * @author mimose
 * @description provide specific ArgValueGenerator (Jackson)
 * @date 2021/3/25
 */
public class ArgValueGenerator implements Generator {

    public final static ObjectMapper MAPPER;

    static {
        MAPPER = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    @Override
    public String generate(Object[] args) {
        try {
            return MAPPER.writeValueAsString(args);
        } catch (JsonProcessingException e) {
            throw new GenException("ArgValueGenerator (Jackson) - gen error", e);
        }
    }
}
