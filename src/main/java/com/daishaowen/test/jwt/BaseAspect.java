package com.daishaowen.test.jwt;

import com.daishaowen.test.exception.FrameworkException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * [抽象的基础切面类]
 *
 * @author Chris li[黎超]
 * @version [版本, 2017-04-12]
 * @see
 */
public abstract class BaseAspect {
    /**
     * [通过织入点获取源方法]
     *
     * @author Chris li[黎超]
     * @version [版本, 2017-04-12]
     */
    protected Method getSourceMethod(JoinPoint joinPoint) {
        Method proxyMethod = ((MethodSignature) joinPoint.getSignature()).getMethod();
        try {
            return joinPoint.getTarget().getClass().getMethod(proxyMethod.getName(), proxyMethod.getParameterTypes());
        } catch (Exception e) {
            throw new FrameworkException("Error when execute method[PermissionAspect.getSourceMethod].", e);
        }
    }
}
