package com.mimose.component.deduplicate.utils;

import com.mimose.component.deduplicate.annotations.Level;
import com.mimose.component.deduplicate.exceptions.LoaderException;

import java.util.ServiceLoader;

/**
 * @author mimose
 * @description Loader -- implemented the SPI mechanism
 * @date 2021/3/25
 */
public final class Loader {

    /**
     * load the object about this class, by SPI
     * @param clazz
     * @param <T>
     * @return
     * @throws LoaderException
     */
    public static <T> T load(Class<T> clazz) throws LoaderException {
        return Loader.load(clazz, Loader.class.getClassLoader());
    }

    /**
     * load the object about this class, by SPI
     * @param clazz
     * @param classLoader
     * @param <T>
     * @return
     * @throws LoaderException
     */
    public static <T> T load(Class<T> clazz, ClassLoader classLoader) throws LoaderException {
        final ServiceLoader<T> loaders = ServiceLoader.load(clazz, classLoader);
        if(loaders == null) {
            throw new LoaderException();
        }
        int maxPriority = Level.DEFAULT_PRIORITY;
        T service = null;
        for (T loader : loaders) {
            final Level level = loader.getClass().getAnnotation(Level.class);
            int priority = level == null ? Level.DEFAULT_PRIORITY : level.priority();
            if(service == null || priority > maxPriority) {
                service = loader;
                maxPriority = priority;
            }
        }
        return service;
    }
}
