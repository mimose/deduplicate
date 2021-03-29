package com.mimose.component.deduplicate.proxy;

import java.lang.reflect.Proxy;
import java.util.Objects;

/**
 * @author mimose
 * @description
 * @date 2021/3/26
 */
public final class Proxies {

    public static <T> T proxy(final T object) {
        if(Objects.isNull(object)) {
            return (T) new NullProxy(object).wrap();
        }

        final Class clazz = object.getClass();

        if (clazz.isInterface() || Proxy.isProxyClass(clazz)) {
            return (T) new DynamicProxy(object).wrap();
        }

        return (T) new CglibProxy(object).wrap();
    }
}
