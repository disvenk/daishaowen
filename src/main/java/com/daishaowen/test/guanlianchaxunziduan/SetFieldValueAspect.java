package com.daishaowen.test.guanlianchaxunziduan;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@Aspect
public class SetFieldValueAspect {
    @Autowired
    SetFieldValueUtil setFieldValueUtil;

    @Around("@annotation(needSetFieldValue)")
    public Object doSetFieldValue(ProceedingJoinPoint pjp,NeedSetFieldValue needSetFieldValue) throws Throwable{
        Object ret = pjp.proceed();
        if(ret instanceof Collection){
            this.setFieldValueUtil.setValue((Collection) ret);
        }
        return ret;
    }

}
