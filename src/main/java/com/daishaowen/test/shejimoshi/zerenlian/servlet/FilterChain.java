package com.daishaowen.test.shejimoshi.zerenlian.servlet;

import java.util.ArrayList;
import java.util.List;

//定义内部处理的filter链，链中保存真正filter的执行顺序，和servlet
public class FilterChain {
    //位置标记
    private int cursor;
    public List<Filter> filters=new ArrayList<Filter>();
    public Servlet servlet;
    public void setServlet(Servlet servlet){
        this.servlet=servlet;
    }
    public void addFilter(Filter filter){
        this.filters.add(filter);
    }


    public void doFilter(String request,String response){
        //在过滤器都执行完毕才执行servlet
        if(cursor<filters.size()){
            filters.get(cursor++).doFilter(request, response,this);
        }else {
            servlet.service(request, response);
        }
    }
}
