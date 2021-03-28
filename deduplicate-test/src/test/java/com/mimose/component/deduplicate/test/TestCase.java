package com.mimose.component.deduplicate.test;

import com.mimose.component.deduplicate.proxy.Proxies;
import org.junit.Test;

/**
 * @author mimose
 * @description
 * @date 2021/3/26
 */
public class TestCase {

    @Test
    public void error() {
        try {
            TestService service = Proxies.proxy(new TestService());
            service.getOne("1");
            service.getOne("1");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void success() throws InterruptedException {
        TestService service = Proxies.proxy(new TestService());
        service.getOne("1");
        Thread.sleep(1500);
        service.getOne("1");
    }
}
