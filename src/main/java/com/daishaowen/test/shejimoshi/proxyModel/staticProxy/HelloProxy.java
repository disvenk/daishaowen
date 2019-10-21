package com.daishaowen.test.shejimoshi.proxyModel.staticProxy;

import com.daishaowen.test.shejimoshi.proxyModel.publicInterface.Hello;
import com.daishaowen.test.shejimoshi.proxyModel.publicInterface.HelloImpl;

/**
 * Created by disvenk.dai on 2018-11-07 14:32
 * 代理类，接口代理
 */
public class HelloProxy implements Hello {
    private Hello hello;

    //这里就是静态代理的缺陷，我们只能在这里写死为某个类做代理
    public HelloProxy(){
        hello = new HelloImpl();
    }

    public void  before(){
        System.out.println("before");
    }

    public void after(){
        System.out.println("after");
    }

    @Override
    public void say(String name){
        before();
        hello.say(name);
        after();
    }

    public static void main(String[] args){
        Hello hello = new HelloProxy();
        hello.say("Tom");
    }
}
