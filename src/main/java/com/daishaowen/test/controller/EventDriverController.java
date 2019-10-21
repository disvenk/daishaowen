package com.daishaowen.test.controller;

import com.daishaowen.test.shijianqudong.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("eventDriver")
public class EventDriverController {
    @Autowired
    UserService userService;

    @RequestMapping("userRegister")
    public String userRegister(){
        userService.register("大蚊子");
        return "注册成功";
    }
}
