package com.mimose.component.deduplicate.sample.java;

import com.mimose.component.deduplicate.proxy.Proxies;
import com.mimose.component.deduplicate.starter.DeduplicateStarter;

/**
 * duplicate attack append
 * @author mimose
 * @description
 * @date 2021/4/18
 */
public class TestErrorCase {

    public static void main(String[] args) {
        DeduplicateStarter.start();

        TestService service = Proxies.proxy(new TestService());
        service.getOne("123");
        System.out.println("TestService getOne, id=123");
        service.getOne("123");
        System.out.println("TestService getOne, id=123");
    }
}
