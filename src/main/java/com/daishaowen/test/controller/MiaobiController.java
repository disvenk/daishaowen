package com.daishaowen.test.controller;

import com.daishaowen.test.miaobishenghua.Tuser;
import com.daishaowen.test.serviceImpl.MiaoBiShengHua;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("miaobi")
public class MiaobiController {

    @Autowired
    MiaoBiShengHua miaoBiShengHua;

    @RequestMapping("miaobi1")
    @ResponseBody
    public String miaobi1(){
        Tuser tuser = new Tuser();
        tuser.setId("我是大蚊子");
        miaoBiShengHua.doSomething(tuser);
        return "成功";
    }
}
