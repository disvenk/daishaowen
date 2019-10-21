package com.daishaowen.test.shejimoshi.zerenlian.servlet;

//过滤器接口
public interface Filter {
    //request 和response在真正的servlet中是对象，此处简化处理为string
    public abstract void doFilter(String request,String response,FilterChain filterChain);
}
