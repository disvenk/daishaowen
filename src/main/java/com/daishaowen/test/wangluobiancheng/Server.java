package com.daishaowen.test.wangluobiancheng;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static int port = 3007;

    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(port);
            System.out.println("开始在"+ port +"端口启动服务器...");

            String clientMsg, serverMsg="";
            Scanner sc = new Scanner(System.in);
            while (!serverMsg.equals("bye")) {
                Socket socket = ss.accept();
                System.out.println("链接已建立");
                //InputStream input = s.getInputStream();
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                //OutputStream output = s.getOutputStream();
                BufferedWriter output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));


                InputReader tIntput = new InputReader(input, "client");
                tIntput.start();


                while (!serverMsg.equals("bye")) {
//                  System.out.println("client says : " + clientMsg);
//
                    serverMsg = sc.nextLine();
                    //后面必须要加 \r 换行符
                    output.write(serverMsg + "\r");
                    output.flush();

                }
                tIntput.isRun = false;
                socket.close();
                sc.close();
                System.out.println("server关闭连接");
            }
            ss.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
