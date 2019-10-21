package com.daishaowen.test.NIOReadAndWrite;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by disvenk.dai on 2018-10-29 15:10
 */
public class NIOVSIO {
    public static void readIO() throws Exception{
        File file = new File("E:/迅雷下载/美人鱼.HD.720p.国语中字.rmvb");
        File file2 = new File("E:/迅雷下载/美人鱼.HD.720p.国语中字io.rmvb");
        FileInputStream fis = new FileInputStream(file);
        FileOutputStream fos = new FileOutputStream(file2);
        byte[] buffer = new byte[1024];
        int len = -1;
        while((len = fis.read(buffer))!= -1){
            fos.write(buffer, 0, len);
        }
        fis.close();
        fos.close();
    }
    public static void readNIO() throws Exception{
        File file = new File("E:/迅雷下载/美人鱼.HD.720p.国语中字.rmvb");
        File file3 = new File("E:/迅雷下载/美人鱼.HD.720p.国语中字nio.rmvb");
        FileInputStream fis = new FileInputStream(file);
        FileOutputStream fos = new FileOutputStream(file3);
        FileChannel channel = fis.getChannel();
        FileChannel outchannel = fos.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while(channel.read(buffer)!=-1){
            buffer.flip();
            outchannel.write(buffer);
            buffer.clear();
        }
        channel.close();
        fis.close();
        fos.close();
    }
    public static void main(String[] args) throws Exception
    {

        long nioStart = System.currentTimeMillis();
        readNIO();
        System.out.println("nio-time: "+((System.currentTimeMillis()-nioStart)));
        long ioStart = System.currentTimeMillis();
        readIO();
        System.out.println("io-time: "+((System.currentTimeMillis()-ioStart)));
    }
}
