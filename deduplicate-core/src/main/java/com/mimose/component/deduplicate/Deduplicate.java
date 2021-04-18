package com.mimose.component.deduplicate;

import com.mimose.component.deduplicate.annotations.Deduplicated;
import com.mimose.component.deduplicate.exceptions.DuplicateException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;

/**
 * @author mimose
 * @description do duplicate check
 * @date 2021/3/26
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Deduplicate {

    public static void de(Method method, Object[] args) throws DuplicateException {
        if(!method.isAnnotationPresent(Deduplicated.class)) {
            return;
        }

        final Deduplicated annotation = method.getAnnotation(Deduplicated.class);
        DeduplicateSupport.newInstance()
                .method(method)
                .params(args)
                .ttl(annotation.ttl())
                .tokenGAid(annotation.tokenGAid())
                .tokenGType(annotation.tokenGType())
                .work();
    }

}
