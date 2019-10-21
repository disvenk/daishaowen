package com.daishaowen.test.shejimoshi.zerenlian.servlet;

//处理xss攻击
public class XssFilter implements Filter {
    @Override
    public void doFilter(String request, String response, FilterChain filterChain) {
        System.out.println("过滤request的xss内容");
        //第三次执行的时候cursor等于3，不满足条件走else执行servlet
        filterChain.doFilter(request, response);
        System.out.println("过滤response的xss内容");
    }
}
