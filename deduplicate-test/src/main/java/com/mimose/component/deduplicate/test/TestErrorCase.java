package com.mimose.component.deduplicate.test;

import com.mimose.component.deduplicate.exceptions.DuplicateException;
import com.mimose.component.deduplicate.proxy.Proxies;

/**
 * Copyright (C) 2020 广东百慧科技有限公司
 *
 * <p></p>
 *
 * @author ccy
 * @version 0.0.1
 * @since 1.8
 */
public class TestErrorCase {

    public static void main(String[] args) {
        try {
            TestService service = Proxies.proxy(new TestService());
            service.getOne("123");
            System.out.println("TestService getOne, id=123");
            service.getOne("123");
            System.out.println("TestService getOne, id=123");
        } catch (Exception e) {
        }
    }
}
