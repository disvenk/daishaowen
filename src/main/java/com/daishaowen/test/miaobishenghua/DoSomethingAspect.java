package com.daishaowen.test.miaobishenghua;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@Aspect
public class DoSomethingAspect {

    @Around("@annotation(ds)")
    public Object doAround(ProceedingJoinPoint pjp, DoSomething ds){
        System.out.println("获取到key是"+getKey(ds.key(),pjp));
        System.out.println("加了缓存");
        Object ret = null;
        try {
            ret = pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("加了日志");
        return ret;
    }

    private String getKey(String key,ProceedingJoinPoint pjp){
        Method method = ((MethodSignature)pjp.getSignature()).getMethod();
        String[] parameterNames = new LocalVariableTableParameterNameDiscoverer().getParameterNames(method);
        return SpelParser.getKey(key,parameterNames,pjp.getArgs());
    }
}
