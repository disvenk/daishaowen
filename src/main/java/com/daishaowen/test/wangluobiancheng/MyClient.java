package com.daishaowen.test.wangluobiancheng;

/**
 * Created by bruce on 2018-03-29 09:39
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MyClient {

    public static void main(String[] args) throws Exception{

        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;

        BufferedReader input = null;
        // 请求指定ip和端口号的服务器
        socket = new Socket("127.0.0.1",3333);

        while(true){
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out = new PrintWriter(socket.getOutputStream(), true);
            // 接收控制台的输入
            input = new BufferedReader(new InputStreamReader(System.in));
           //读出控制台输入的信息，
            String info = input.readLine();

            //发送到服务端
            out.println(info);

            //监听，这里会阻塞直到读到数据为止
            String str = in.readLine();

            System.out.println("客户端显示--》服务器的信息：" + str);
        }
        //in.close();
        //out.close();
    }

    //总结：服务端一启动就会开始监听读，而客户端是监听控制台的写，所以要客户端先发送数据
    //服务端接收到数据后，转变成监听控制台的写，而客户端转变为监听读

}
