package com.daishaowen.test.redisMoni;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SeverSocket {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8880);
        Socket receive = serverSocket.accept();
        byte[] bytes = new byte[1024];
        receive.getInputStream().read(bytes);
        System.out.println(new String(bytes));
    }
}
