package com.mimose.component.deduplicate.sample.java;

import com.mimose.component.deduplicate.proxy.Proxies;
import com.mimose.component.deduplicate.starter.DeduplicateStarter;

/**
 * duplicate attack dont append
 * @author mimose
 * @description
 * @date 2021/3/28
 */
public class TestSuccessCase {

    public static void main(String[] args) throws InterruptedException {
        DeduplicateStarter.start();

        TestService service = Proxies.proxy(new TestService());
        service.getOne("123");
        System.out.println("TestService getOne, id=123");
        Thread.sleep(1500);
        service.getOne("123");
        System.out.println("TestService getOne, id=123");
    }
}
