package com.daishaowen.test.NIOReadAndWrite;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by disvenk.dai on 2018-10-29 15:15
 */
public class TestFileChannel {
    public static void main(String[] args) throws IOException {
        FileInputStream fin = new FileInputStream("D:\\temp\\TestService.cs");

        // 获取通道
        FileChannel fc = fin.getChannel();

        // 创建缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        // 读取数据到缓冲区
        fc.read(buffer);

        buffer.flip();

        StringBuffer s=new StringBuffer();
        while (buffer.remaining() > 0) {
            byte b = buffer.get();
            s.append((char)b);
            //System.out.print(((char) b));
        }
        System.out.print(s);

        fin.close();
    }
}
