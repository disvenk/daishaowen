package com.daishaowen.test.wangluobiancheng;

/**
 * Created by disvenk.dai on 2018-03-29 09:43
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServices {

    public static void main(String[] args) throws Exception{
        ServerSocket ss = null;
        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;
        BufferedReader input = null;

        // 监听3333端口
        ss = new ServerSocket(3333);
        // 等待接收客户端的请求
        socket = ss.accept();
        while(true){
            // 获取连接对象的输入流
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // 获取客户端的输入信息,服务端一开始会读，所以这一行放在前面，
            //但是也会阻塞在这里，所以一定要等客户端先发数据
            //监听，这里会阻塞直到读到数据为止
            String str = in.readLine();

            System.out.println("服务器显示-->客户端输入数据：" + str);

            //使用一个输入对象来接收控制台输入的信息
            input = new BufferedReader(new InputStreamReader(System.in));

            //得到一个输出对象
            out = new PrintWriter(socket.getOutputStream(),true);
            //将控制台的数据读出来，走到这里就会等待控制台输入，如果不输入就是一直阻塞
            String info = input.readLine();
            // 将数据输出到客户端
            out.println(info);
        }
//        in.close();
//        out.flush();
//        out.close();
    }

}