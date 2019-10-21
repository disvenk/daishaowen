package com.daishaowen.test.shejimoshi.zerenlian.servlet;

public class MainServlet implements Servlet {
    @Override
    public void service(String request, String response) {
        System.out.println(request);
        //为response赋值
        response="返回结果";
        System.out.println(response);
    }
}
