package com.mimose.component.deduplicate.test;

import com.mimose.component.deduplicate.annotations.Deduplicated;
import com.mimose.component.deduplicate.log.FluentLogger;

import javax.servlet.http.HttpServletRequest;

/**
 * @author mimose
 * @description
 * @date 2021/3/26
 */
public class TestService {
    private static final FluentLogger LOGGER = FluentLogger.getLogger(TestService.class);

    @Deduplicated(ttl = 1)
    public String getOne(String id) {
        LOGGER.debug().module("TEST").message("getOne, id: {}").args(id).build();
        return "success";
    }


    @Deduplicated(ttl = 1)
    public String getOne(HttpServletRequest request, String id) {
        LOGGER.debug().module("TEST").message("getOne, id: {}").args(id).build();
        return "success";
    }
}
