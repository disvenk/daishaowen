package com.daishaowen.test.redisMoni;

import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class JedisClientTest {
    public static int arrayLength=10000;
    public static String ip="127.0.0.1";
    public static int port = 8888;

    public static void main(String[] args) throws IOException {
//        Jedis jedis = new Jedis(ip,port);
//        jedis.set("disvenk","dawenzi");
//        jedis.close();


        Socket socket = new Socket("127.0.0.1",8880);
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("disvenk".getBytes());
    }
}
