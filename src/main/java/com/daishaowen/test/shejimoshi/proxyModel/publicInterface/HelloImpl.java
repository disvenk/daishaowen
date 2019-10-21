package com.daishaowen.test.shejimoshi.proxyModel.publicInterface;

/**
 * Created by disvenk.dai on 2018-11-07 14:31
 * 委托类
 */
public class HelloImpl implements Hello {
    @Override
    public void say(String name){
        System.out.println("hello"+name);
    }
}
