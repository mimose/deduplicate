package com.mimose.component.deduplicate.instances;

/**
 * @author mimose
 * @description Instance Helper
 * @date 2021/3/25
 */
public final class Instance {
    private Instance() {}

    /**
     * return a singleton
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T singleton(Class<T> clazz) {
        return InstanceFactory.getInstance().singleton(clazz);
    }

    /**
     * return a new instance
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T prototype(Class<T> clazz) {
        return InstanceFactory.getInstance().prototype(clazz);
    }
}
