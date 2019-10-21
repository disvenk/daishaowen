package com.daishaowen.test.bingfa;

import com.daishaowen.test.httpClient.HttpRequest;

import java.util.concurrent.CountDownLatch;

public class Test1 {
    //模拟的请求量
    private static final int threadNum=1;

    //倒计时，用于模拟并发
    private CountDownLatch  cdl = new CountDownLatch(threadNum);

   /* @Before
    //测试前将数据放在缓存中
    public void init(){
        //to do anyThing....
    }*/

    @org.junit.Test
    //主测试方法，实例化threadNum个线程，并同时执行查询
    public void test1(){
        //定义窗口个数

        //线程池
        for(int i=0;i<threadNum;i++){
            new Thread(new UserRequest()).start();
            cdl.countDown();//计数器，每实例化一个线程计数器减一，知道模拟的线程数实例化完，当计数器为0的那一瞬间，所有线程同时停止等待
        }

        try{
            //阻塞主线程，等待所有子线程运行完毕
            Thread.currentThread().join();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    private class UserRequest implements Runnable{


        @Override
        public void run(){
            try{
                cdl.await();//已实例化的线程在此等待，等所有的线程实例化完毕，同时停止等待
            }catch(InterruptedException e){
                e.printStackTrace();
            }

            //要执行的内容。。。。
            try {

               // String s = HttpRequest.sendGet("http://test0001.restoplus.cn/wechat/index?brandId=1&shopId=26", null);
                //String s = HttpRequest.sendGet("http://zanshang.restoplus.cn/wechat/index?brandId=1&shopId=26&web=open&userId=00026df09014462992eb710b0cb2e2c7", null);
               //String s = HttpRequest.sendPost("http://localhost:8084/redis2", null);
                //System.out.println(s);
                HttpRequest.sendPost("http://localhost:8088/refresh", null);

            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }

}

