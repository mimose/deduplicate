package com.mimose.component.deduplicate.utils;

import org.apache.commons.lang3.ObjectUtils;

import java.util.function.Supplier;

/**
 * @author mimose
 * @description
 * @date 2021/4/18
 */
public final class ObjectUtil extends ObjectUtils {
    private ObjectUtil() {}

    public static <T> T requireNonNull(T obj, Supplier<RuntimeException> nullExSupplier) {
        if(obj == null) {
            throw requireNonEmpty(nullExSupplier, "[ObjectUtil - requireNonNull] _ nullExSupplier is null").get();
        }
        return obj;
    }

    public static <T> T requireNonNull(Supplier<T> objSupplier, Supplier<RuntimeException> nullExSupplier) {
        return requireNonNull(
                requireNonEmpty(objSupplier.get(), "[ObjectUtil - requireNonNull] _ objSupplier is null"),
                nullExSupplier
        );
    }
}
