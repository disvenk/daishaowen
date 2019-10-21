package com.daishaowen.test.shejimoshi.zerenlian.servlet;

//字符集处理
public class EncodeFilter implements Filter {
    @Override
    public void doFilter(String request, String response, FilterChain filterChain) {
        System.out.println("对request做utf-8编码");
        //执行这里相当于执行xssFilter
        //第二次执行doFilter后cursor等于2
        filterChain.doFilter(request, response);
        System.out.println("对response做utf-8编码");
    }
}
