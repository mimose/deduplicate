package com.mimose.component.deduplicate.instances;

import com.mimose.component.deduplicate.exceptions.ActionException;
import com.mimose.component.deduplicate.utils.Assert;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author mimose
 * @description Instance Factory
 * @date 2021/3/25
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class InstanceFactory {
    private static volatile InstanceFactory factory;
    private static final Integer SINGLETON_MAP_CAPACITY = 16;
    private Map<String, Object> singletonMap;

    /**
     * return a singleton
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T singleton(Class<T> clazz) {
        Assert.notNull(clazz, "InstanceFactory Fail, clazz is null");
        String key = clazz.getName();
        T instance;
        if ((instance = (T) singletonMap.get(key)) == null) {
            singletonMap.put(key, instance = this.prototype(clazz));
        }
        return instance;
    }

    /**
     * return a new instance
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T prototype(Class<T> clazz) {
        Assert.notNull(clazz, "InstanceFactory Fail, clazz is null");
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            throw new ActionException.NormalActionException("InstanceFactory Fail, newInstance fail", e);
        }
    }

    /**
     * return a singleton factory
     * @return
     */
    public static InstanceFactory getInstance() {
        if(factory == null) {
            synchronized (InstanceFactory.class) {
                if(factory == null) {
                    factory = new InstanceFactory();
                    factory.singletonMap = new ConcurrentHashMap<>(SINGLETON_MAP_CAPACITY);
                }
            }
        }
        return factory;
    }
}
