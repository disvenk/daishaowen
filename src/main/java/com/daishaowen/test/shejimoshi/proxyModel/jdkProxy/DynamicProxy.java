package com.daishaowen.test.shejimoshi.proxyModel.jdkProxy;

import com.daishaowen.test.shejimoshi.proxyModel.publicInterface.Hello;
import com.daishaowen.test.shejimoshi.proxyModel.publicInterface.HelloImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by disvenk.dai on 2018-11-07 14:38
 */
public class DynamicProxy implements InvocationHandler {
        //目标对象(真实对象)
        private Object target;

        public DynamicProxy (Object obj){
                this.target = obj;
                }

        public void before(){
                System.out.println("before");
                }

        public void after(){
                System.out.println("after");
                }

        //重写invoke方法
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable{
                before();
                //这里的这个method是代理对象调用的那个方法，底层通过反射来实现的，args就是调用方法时传入的参数，这里可以传多个参数
                //只要把代理类的那个方法的参数改成多个就可以了
                //method是被代理的方法
                //target是目标对象，args是参数
            if(true){
                return null;//如果不想执行目标方法，就在执行目标方法之前返回
            }
                Object result = method.invoke(target,args);
                after();
                return null;
                }

        //创建代理对象
        public Object createProxy(){
                //使用Proxy完成代理对象创建
                //得到目标对象的ClassLoader
                ClassLoader loader = target.getClass().getClassLoader();
                //得到目标对象的实现接口的class[]
                Class[] interfaces = target.getClass().getInterfaces();
                //第三个参数需要一个实现了InvocationHadler接口的对象
                return Proxy.newProxyInstance(loader,interfaces,this);
                }

public static void main(String[] args){
        //创建目标对象
        Hello hello = new HelloImpl();
        //创建代理对象
        DynamicProxy dyp = new DynamicProxy(hello);
        Hello helloProxy = (Hello) dyp.createProxy();
//经过代理对象创建出来的目标对象，所调用的方法必然会经过一层代理的
        helloProxy.say("SB");
        }
}
