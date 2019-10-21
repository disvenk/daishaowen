package com.daishaowen.test.aop.chuantongaop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.AfterAdvice;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.lang.Nullable;

import java.lang.reflect.Method;

public class OrderHelper implements MethodBeforeAdvice,AfterReturningAdvice, MethodInterceptor {


    @Override
    public void before(Method method, Object[] args, @Nullable Object target) throws Throwable {
        System.out.println("前置通知");
    }

    @Override
    public void afterReturning(@Nullable Object returnValue, Method method, Object[] args, @Nullable Object target) throws Throwable {
        System.out.println("后置通知");
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("环绕前");
        Object value = invocation.proceed();
        System.out.println("环绕后");
        return value;
    }
}
