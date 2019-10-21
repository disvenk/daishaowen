package com.daishaowen.test.wangluobiancheng;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Socket s = new Socket("localhost", 3007);
            //InputStream input = s.getInputStream();
            BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
            //OutputStream output = s.getOutputStream();
            BufferedWriter output = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));

            String serverMsg;

            InputReader tIntput = new InputReader(input, "server");
            tIntput.start();

            Scanner sc = new Scanner(System.in);
            String clientMsg = sc.nextLine();

            while(true){
                //必须要在后面加一个 \r 换行符
                output.write(clientMsg +"\r");
                output.flush();

//              serverMsg = input.readLine();
//              System.out.println("server says : "+ serverMsg);
                if(clientMsg.equals("bye")) break;
                clientMsg = sc.nextLine();
            }
            tIntput.isRun = false;
            sc.close();
            s.close();
            System.out.println("client关闭连接");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
