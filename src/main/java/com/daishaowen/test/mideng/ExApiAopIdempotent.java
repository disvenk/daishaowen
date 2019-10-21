//package com.daishaowen.test.mideng;
//
//import com.alibaba.fastjson.JSONObject;
//import org.apache.commons.lang3.StringUtils;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.PrintWriter;
//
//@Component
//@Aspect
//public class ExApiAopIdempotent {
//
//    @Autowired
//    private RedisTokenService tokenService;
//
//    @Pointcut("execution(public * com.daishaowen.test.controller.*.*(..))")
//    public void rlAop(){}
//
//    @Before("rlAop()")
//    public void before(JoinPoint point) {
//        MethodSignature signature = (MethodSignature) point.getSignature();
//        ExtApiToken annotation = signature.getMethod().getAnnotation(ExtApiToken.class);
//        if (annotation != null) {
//            getRequest().setAttribute("jwt", tokenService.getToken());
//        }
//    }
//
//    @Around("rlAop()")
//    public Object doBefore(ProceedingJoinPoint joinPoint) throws Throwable {
//        //判断方法上是否有ExApiIdempotent注解
//        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
//        ExApiIdempotent exApiIdempotent = methodSignature.getMethod().getAnnotation(ExApiIdempotent.class);
//        HttpServletRequest request = getRequest();
//        String jwt = null;
//        if(exApiIdempotent!=null){
//            String type = exApiIdempotent.type();
//            if(type.equals("header")){
//                 jwt = request.getHeader("jwt");
//            }else {
//                jwt = request.getParameter("jwt");
//            }
//
//            if(jwt==null || StringUtils.isEmpty(jwt)){
//                JSONObject jsonObject = new JSONObject();
//                jsonObject.put("code",1001);
//                jsonObject.put("msg","参数错误");
//                response(jsonObject);
//                return null;
//            }
//
//            //查询redis中该令牌删除，并返回结果Boolean，但是在提交的过程中可能出现异常，那个这个token
//            //就只能使用一次，所以要返回一个标识，如果为false则要重新生成令牌
//            boolean isToken = tokenService.findToken(jwt);
//            if(!isToken){
//                JSONObject jsonObject = new JSONObject();
//                jsonObject.put("code",1002);
//                jsonObject.put("msg","请勿重复提交");
//                response(jsonObject);
//                return null;
//            }
//        }
//        //放行执行正常业务
//        System.out.println("进入了幂等切面");
//        Object procced = joinPoint.proceed();
//        return procced;
//    }
//
//    public HttpServletRequest getRequest(){
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//        return request;
//    }
//
//    public void response(JSONObject msg){
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletResponse response = attributes.getResponse();
//        //设置浏览器解码是用UTF-8的码表来解码
//        response.setContentType("application/json;charset=UTF-8");
//        response.setHeader("Cache-Control","no-cache");
//        PrintWriter out = null;
//        try{
//            out = response.getWriter();
//            out.write(msg.toJSONString());
//        }catch (Exception e){
//            e.printStackTrace();
//        }finally {
//            if(out!=null){
//                out.close();
//            }
//        }
//    }
//
//}
