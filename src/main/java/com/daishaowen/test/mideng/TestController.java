//package com.daishaowen.test.mideng;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.servlet.http.HttpServletRequest;
//
//public class TestController {
//
//    @Autowired
//    RedisTokenService tokenService;
//
//    //不管是from表单提交还是ajax提交，让页面加载时加事先将token生成后放在指定的位置，提交的时候只取那一个
//    @RequestMapping(value = "/indexPage")
//    @ExtApiToken
//    public String indexPage(HttpServletRequest request) {
//        System.out.println("================================");
//        //加上注解ExtApiToken，使用AOP方式统一设置token
//        //request.setAttribute("jwt",tokenService.getToken());
//        return "indexPage";
//    }
//
//    @RequestMapping(value = "/addUserPage")
//    @ResponseBody
//    @ExApiIdempotent(type = "form")
//    public String addUserPage(HttpServletRequest request) {
//        return "添加成功！";
//    }
//
//}
