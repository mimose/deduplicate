package com.mimose.component.deduplicate.utils;

import com.mimose.component.deduplicate.exceptions.ActionException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * @author mimose
 * @description
 * @date 2021/5/1
 */
public final class SpringUtils {
    private SpringUtils() {}

    public static Method getMethod(ProceedingJoinPoint joinPoint) {
        try {
            Signature sig = joinPoint.getSignature();
            MethodSignature msig = (MethodSignature)sig;
            Object target = joinPoint.getTarget();
            return target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
        } catch (NoSuchMethodException e) {
            throw new ActionException.NormalActionException("Fail to get current Method from ProceedJointPoint", e);
        }
    }
}
