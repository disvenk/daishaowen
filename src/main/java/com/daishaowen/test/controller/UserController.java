package com.daishaowen.test.controller;

import com.daishaowen.test.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @RequestMapping("getName")
    public List<String> getName(){
        return  userService.getName();
    }

    @RequestMapping("updateName")
    public String updateName(){
         userService.updateName();
         return "success";
    }
}
