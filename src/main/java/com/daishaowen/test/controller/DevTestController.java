//package com.daishaowen.test.controller;
//
//import com.daishaowen.test.model.User;
//import com.daishaowen.test.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("test")
//public class DevTestController {
//
//    @Autowired
//    UserService userService;
//
//    @RequestMapping("addUser")
//    public int addUser(){
//        User user = new User();
//        user.setUsername("大蚊子");
//        user.setName("代绍文");
//        return userService.addUser(user);
//    }
//}
