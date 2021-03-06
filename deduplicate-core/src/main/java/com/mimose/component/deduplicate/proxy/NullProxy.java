package com.mimose.component.deduplicate.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author mimose
 * @description
 * @date 2021/3/26
 */
public final class NullProxy implements InvocationHandler, Proxy {
    private final Object proxyTarget;

    public NullProxy(Object proxyTarget) {
        this.proxyTarget = proxyTarget;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(this.proxyTarget, args);
    }

    @Override
    public Object wrap() {
        return this.proxyTarget;
    }
}
