package com.mimose.component.deduplicate.gen.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mimose.component.deduplicate.annotations.Level;
import com.mimose.component.deduplicate.exceptions.GenException;
import com.mimose.component.deduplicate.gen.api.ArgValueGenerator;

/**
 * @author mimose
 * @description provide specific ArgValueGenerator (Jackson)
 * @date 2021/3/25
 */
@Level(priority = 1)
public class JacksonArgValueGenerator implements ArgValueGenerator {

    public final static ObjectMapper MAPPER;

    static {
        MAPPER = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    @Override
    public String generate(Object[] args) {
        try {
            return MAPPER.writeValueAsString(args);
        } catch (JsonProcessingException e) {
            throw new GenException("JacksonArgValueGenerator - gen error", e);
        }
    }
}
