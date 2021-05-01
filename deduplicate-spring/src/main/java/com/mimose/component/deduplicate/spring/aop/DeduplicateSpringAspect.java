package com.mimose.component.deduplicate.spring.aop;

import com.mimose.component.deduplicate.Deduplicate;
import com.mimose.component.deduplicate.annotations.Deduplicated;
import com.mimose.component.deduplicate.utils.SpringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.lang.reflect.Method;

/**
 * @author mimose
 * @description the deduplicate aspect
 * @date 2021/5/1
 */
@Aspect
public class DeduplicateSpringAspect {

    @Pointcut("@annotation(com.mimose.component.deduplicate.annotations.Deduplicated)")
    public void deduplicate() {}

    @Around("deduplicate()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        final Method method = SpringUtils.getMethod(joinPoint);

        if(method.isAnnotationPresent(Deduplicated.class)) {
            Deduplicate.de(method, joinPoint.getArgs());
        }

        return joinPoint.proceed();
    }

}
