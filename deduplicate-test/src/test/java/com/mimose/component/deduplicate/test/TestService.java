package com.mimose.component.deduplicate.test;

import com.mimose.component.deduplicate.annotations.Deduplicated;

/**
 * @author mimose
 * @description
 * @date 2021/3/26
 */
public class TestService {

    @Deduplicated(ttl = 1)
    public String getOne(String id) {
        return "success";
    }
}
