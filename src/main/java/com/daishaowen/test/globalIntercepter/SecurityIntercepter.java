//package com.daishaowen.test.globalIntercepter;
//
//import com.alibaba.fastjson.JSONObject;
//import com.daishaowen.test.redis.JedisClientSingle;
//import com.daishaowen.test.xianliu.RequestCount;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.PrintWriter;
//
//public class SecurityIntercepter implements HandlerInterceptor {
//
//    @Autowired
//    JedisClientSingle jedisClientPool;
//
//    @Autowired
//    RequestCount requestCount;
//
//    public String getRemoteHost(HttpServletRequest request){
//        String ip = request.getHeader("x-forwarded-for");
//        if(ip == null || ip.length()==0 || "unKnown".equalsIgnoreCase(ip)){
//            ip = request.getHeader("Proxy-Client-IP");
//        }
//        if(ip == null || ip.length()==0 || "unKnown".equalsIgnoreCase(ip)){
//            ip = request.getHeader("WL-Proxy-Client-IP");
//        }
//        if(ip == null || ip.length()==0 || "unKnown".equalsIgnoreCase(ip)){
//            ip = request.getRemoteAddr();
//        }
//
//        return ip.equals("0:0:0:0:0:0:0:1")? "127.0.0.1":ip;
//    }
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        System.out.println("进入安全拦截器");
//        //String name = request.getParameter("name");
//        String host = getRemoteHost(request);
//
//        JSONObject jsonObject = new JSONObject();
//        //判断ip是否超过了设置的操作上限
//        if(requestCount.isLimitIp(host)){
//            PrintWriter out = null;
//            try{
//                out = response.getWriter();
//                response.setContentType("application/json;charset=UTF-8");
//                response.setHeader("Cache-Control","no-cache");
//                jsonObject.put("code","-2");
//                jsonObject.put("msg","Sorry,This IP operation times is to much,had been limited");
//                out.write(jsonObject.toJSONString());
//            }catch (Exception e){
//                e.printStackTrace();
//            }finally {
//                if(out!=null){
//                    out.close();
//                }
//            }
//            return false;
//        }
//        //response.setContentType("application/json");
//        //response.getWriter().write("{result:'"+Result["Name"]+",你好！}")；
//
//        //判断用户和判断ip一样
//
//        //如果ip没有超过上限，则就ip操作次数加1
//        requestCount.ipCount(host,1);
//        return true;
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,  ModelAndView modelAndView) throws Exception {
//
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,  Exception ex) throws Exception {
//
//    }
//}
