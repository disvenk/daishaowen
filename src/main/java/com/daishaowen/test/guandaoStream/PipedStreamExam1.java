package com.daishaowen.test.guandaoStream;

import com.daishaowen.test.devTest.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by disvenk.dai on 2018-10-09 15:39
 * 在线程sender中向管道中写入一个字符串，写入后关闭该管道流；在线程Reciever中读取该字符串。
 */
public class PipedStreamExam1 {

    public static void main(String[] args){
        ExecutorService executorService = Executors.newCachedThreadPool();

        try {
            //创建管道流
            PipedOutputStream pos = new PipedOutputStream();
            PipedInputStream pis = new PipedInputStream(pos);

            //创建线程对象
            Sender sender = new Sender(pos);
            Reciever reciever = new Reciever(pis);
            Reciever1 reciever1 = new Reciever1(pis);

            //运行子线程
            executorService.execute(sender);
            executorService.execute(reciever);
            executorService.execute(reciever1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //等待子线程结束
        executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class Sender extends Thread {
        private PipedOutputStream pos;

        public Sender(PipedOutputStream pos) {
            super();
            this.pos = pos;
        }

        @Override
        public void run() {
            try {
                String s = "This is a good day. 今天是个好天气。";
                System.out.println("Sender:" + s);
                byte[] buf = s.getBytes();
                pos.write(buf, 0, buf.length);
                pos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static class Reciever extends Thread {
        private PipedInputStream pis;

        public Reciever(PipedInputStream pis) {
            super();
            this.pis = pis;
        }

        @Override
        public void run() {
            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buf = new byte[1024];
                int len = 0;
                while ((len = pis.read(buf)) != -1) {
                    baos.write(buf, 0, len);
                }
                byte[] result = baos.toByteArray();
                String s = new String(result, 0, result.length);
                System.out.println("Reciever:" + s);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static class Reciever1 extends Thread {
        private PipedInputStream pis;

        public Reciever1(PipedInputStream pis) {
            super();
            this.pis = pis;
        }

        @Override
        public void run() {
            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buf = new byte[1024];
                int len = 0;
                while ((len = pis.read(buf)) != -1) {
                    baos.write(buf, 0, len);
                }
                byte[] result = baos.toByteArray();
                String s = new String(result, 0, result.length);
                System.out.println("Reciever:" + s);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
