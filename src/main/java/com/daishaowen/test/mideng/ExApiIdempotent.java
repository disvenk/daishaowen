package com.daishaowen.test.mideng;

import javax.xml.bind.Element;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
* 解决幂等性，支持网络延迟和表单重复提交
* */
@Target(value= ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExApiIdempotent {
    String type();
}
