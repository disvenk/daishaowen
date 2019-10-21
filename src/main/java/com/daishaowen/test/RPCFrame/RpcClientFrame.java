package com.daishaowen.test.RPCFrame;

import org.apache.poi.ss.formula.functions.T;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;

public class RpcClientFrame {

    //远程代理对象
    public static <T> T getRemoteProxyObj(final Class<?> serviceInterface,
                                          final InetSocketAddress address){
            return (T) Proxy.newProxyInstance(serviceInterface.getClassLoader(),
                    new Class<?>[]{serviceInterface},new DynProxy(serviceInterface,address));
    }

    //动态代理类
    private static class DynProxy implements InvocationHandler{
        private final Class<?> serviceInterface;
        private final InetSocketAddress address;

        public DynProxy(Class<?> serviceInterface,InetSocketAddress address){
            this.serviceInterface=serviceInterface;
            this.address=address;
        }

        @Override
        public Object invoke(Object object,Method method,Object[] objects)throws Throwable{
            Socket socket = null;
            ObjectOutputStream outputStream = null;
            ObjectInputStream inputStream = null;

            try{
                socket = new Socket();
                socket.connect(address);

                outputStream = new ObjectOutputStream(socket.getOutputStream());
                //发送客户端的调用请求
                outputStream.writeUTF(serviceInterface.getName());//发送要调用的哪个服务
                outputStream.writeUTF(method.getName());//要调用的方法名
                outputStream.writeObject(objects);//方法参数
                outputStream.writeObject(method.getParameterTypes());//方法的参数类型

                outputStream.flush();
                //接收服务器的应答
                inputStream = new ObjectInputStream(socket.getInputStream());
                return inputStream.readObject();
            }finally {
                if(socket!=null){socket.close();}
                if(outputStream!=null){outputStream.close();}
                if(inputStream!=null){inputStream.close();}
            }
        }
    }
}
