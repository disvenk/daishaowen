package com.daishaowen.test.shejimoshi.proxyModel.cglibProxy;

import com.daishaowen.test.shejimoshi.proxyModel.publicInterface.HelloImpl;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by disvenk.dai on 2018-11-07 14:51
 */
public class CGLibProxy implements MethodInterceptor {
    //声明目标对象
    private Object object;

    public CGLibProxy (Object obj){
        this.object = obj;
    }

    public void before(){
        System.out.println("before");
    }

    public void after(){
        System.out.println("after");
    }

    //创建代理对象
    public Object createProxy(){
        //创建Enhancer
        Enhancer enhancer = new Enhancer();

        //传递目标对象的class
        enhancer.setSuperclass(object.getClass());

        //设置回调操作
        enhancer.setCallback(this);

        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        before();
        if(args[0].equals("NB")){
            System.out.println("他好牛逼，我们玩不过他");
            return null;
        }
        Object result = method.invoke(object,args);
        after();
        return result;
    }

    public static void main(String[] args){
        CGLibProxy cgLibProxy = new CGLibProxy(new HelloImpl());
        HelloImpl proxy = (HelloImpl) cgLibProxy.createProxy();
        proxy.say("NB");
    }
}
