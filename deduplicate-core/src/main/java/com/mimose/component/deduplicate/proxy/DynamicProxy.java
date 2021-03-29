package com.mimose.component.deduplicate.proxy;

import com.mimose.component.deduplicate.Deduplicate;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author mimose
 * @description
 * @date 2021/3/26
 */
@RequiredArgsConstructor
public final class DynamicProxy implements InvocationHandler, Proxy {
    private final Object proxyTarget;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // check deduplicate
        Deduplicate.de(method, args);
        // process
        return method.invoke(proxyTarget, args);
    }

    @Override
    public Object wrap() {
        InvocationHandler handler = new DynamicProxy(this.proxyTarget);
        return java.lang.reflect.Proxy.newProxyInstance(
                handler.getClass().getClassLoader(),
                this.proxyTarget.getClass().getInterfaces(),
                handler
        );
    }
}
