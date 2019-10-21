package com.daishaowen.test.RPCFrame;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class RpcServerFrame {
    //服务的持久String服务名，class实际服务的class
    private static final Map<String,Class> serviceHolder = new HashMap<>();

    //服务的注册
    public void regServer(Class serviceInterface,Class impl){
        serviceHolder.put(serviceInterface.getName(),impl);
    }

    public RpcServerFrame(int port){
        super();
        this.port=port;
    }


    //服务端口号
    private int port;

    //处理服务请求任务
    private static class ServerTask implements Runnable{
        private Socket client = null;
        public ServerTask(Socket client){
            this.client=client;
        }

        @Override
        public void run(){
            try(ObjectInputStream inputStream = new ObjectInputStream(client.getInputStream());
                ObjectOutputStream outputStream = new ObjectOutputStream(client.getOutputStream())
                ){
                //接收客户端的请求
                String serviceName = inputStream.readUTF();
                String methodName = inputStream.readUTF();
                Object[] args = (Object[]) inputStream.readObject();
                Class<?>[] paraType = (Class<?>[]) inputStream.readObject();

                //找到实际服务
                Class serviceClass = serviceHolder.get(serviceName);
                if(serviceClass==null){
                    throw new ClassNotFoundException(serviceClass+"not found");
                }

                //进行业务处理，并返回结果
                Method method = serviceClass.getMethod(methodName,paraType);
                if(method==null){
                    throw new NoSuchMethodException(methodName+"not found");
                }
                Object result = method.invoke(serviceClass.newInstance(), args);

                outputStream.writeObject(result);
                outputStream.flush();

            }catch  (Exception e) {
                e.printStackTrace();
            }finally {
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    //启动RPC服务
    public void startService() throws IOException {
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(port));
        System.out.println("start server on"+port+"运行");
        try{
            while (true){
                new Thread(
                    new ServerTask(serverSocket.accept())).start();
            }
        }finally {
            serverSocket.close();
        }
    }
}
