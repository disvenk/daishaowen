package com.daishaowen.test.bingfa;


import com.daishaowen.test.httpClient.HttpRequest;
import com.daishaowen.test.httpClient.Post;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.InputStream;
import java.util.concurrent.CountDownLatch;

public class Test {
    //模拟的请求量
    private static final int threadNum=1000;

    //倒计时，用于模拟并发
    private CountDownLatch  cdl = new CountDownLatch(threadNum);

   /* @Before
    //测试前将数据放在缓存中
    public void init(){
        //to do anyThing....
    }*/

    @org.junit.Test
    //主测试方法，实例化threadNum个线程，并同时执行查询
    public void test(){
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
                //Post.get();
                //String s = HttpRequest.sendGet("http://test0001.restoplus.cn/wechat/index","brandId=1&shopId=16&web=open&userId=bf37f51bd74e4c55867e6a5933036696");
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost("http://op.eco.restoplus.cn/shop/totalIncome/reportIncome?beginDate=2018-01-01+00%3A00%3A00&endDate=2019-09-11+10%3A54%3A03");
                httpPost.setHeader("Cookie","JSESSIONID=478652e2-6a75-42f9-922d-59f7e3180c76; auth=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1Njg5Mjk2MDgsInVzZXJfbmFtZSI6InhpYW9tYW5mZW4iLCJqdGkiOiI4YjU2NWRhMC1iYWZkLTQ3NmQtODZlYy0yNmFkNDNjZTA4NGYiLCJjbGllbnRfaWQiOiJTSE9QIiwic2NvcGUiOlsicmVhZCIsIndyaXRlIiwidHJ1c3QiXX0.4ZBzoREmiLPIakPFunmtZOlbD3CEOvgDzLDSMYohSmI");
                HttpResponse execute = httpClient.execute(httpPost);
                InputStream inputStream = execute.getEntity().getContent();
                String str = IOUtils.toString(inputStream, "utf-8");
                System.out.println("----");
                System.out.println(str);
               // System.out.println(s);

            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }

}

