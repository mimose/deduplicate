package com.mimose.component.deduplicate.proxy;

import com.mimose.component.deduplicate.Deduplicate;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author mimose
 * @description
 * @date 2021/3/26
 */
public final class CglibProxy implements MethodInterceptor, Proxy {
    private final Object proxyTarget;

    public CglibProxy(Object proxyTarget) {
        this.proxyTarget = proxyTarget;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        // check deduplicate
        Deduplicate.de(method, args);
        // process
        return method.invoke(this.proxyTarget, args);
    }

    @Override
    public Object wrap() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.proxyTarget.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }
}
