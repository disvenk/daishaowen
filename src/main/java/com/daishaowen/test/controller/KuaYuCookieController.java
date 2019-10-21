package com.daishaowen.test.controller;

import com.daishaowen.test.util.CookieUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
@RequestMapping("kuayu")
public class KuaYuCookieController {

    @RequestMapping("test")
    @ResponseBody
    public String test(HttpServletRequest request, HttpServletResponse response){
//        String cookieName = "customer_global_session_id";
//        String encodeString = "UTF-8";
//        String cookieValue = CookieUtils.getCookieValue(request,cookieName,encodeString);
//        if(null==cookieValue || "".equals(cookieValue.trim())){
//            System.out.println("无cookie,生成新的token数据");
//            cookieValue = UUID.randomUUID().toString();
//        }
//
//        CookieUtils.setCookie(request,response,cookieName,cookieValue,0,encodeString);
        return "success";
    }
}
