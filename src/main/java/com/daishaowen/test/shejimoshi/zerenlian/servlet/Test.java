package com.daishaowen.test.shejimoshi.zerenlian.servlet;

public class Test {
    public static void main(String[] args) {
        //定义filter
        Filter encodeFilter=new EncodeFilter();
        Filter xssFilter=new XssFilter();
        FilterChain chain=new FilterChain();
        chain.addFilter(encodeFilter);
        chain.addFilter(xssFilter);

        //定义servlet
        Servlet servlet=new MainServlet();
        chain.setServlet(servlet);
        //第一次执行doFilter后cursor就等于1
        chain.doFilter("发送请求", "");
    }
}
