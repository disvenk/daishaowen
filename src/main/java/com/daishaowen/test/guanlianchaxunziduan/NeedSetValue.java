package com.daishaowen.test.guanlianchaxunziduan;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NeedSetValue {
    //需要用的bean
    Class<?> beanClass();
    //需要传入的参数，如果有多个参数可以使用数组
    String param();
    //需要调用的方法
    String method();
    //需要的结果值
    String targetFiled();
}
