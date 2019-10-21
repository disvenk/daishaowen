package com.daishaowen.test.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("xianLiu")
public class XianLiuController {

    @RequestMapping("xianLiu1")
    public String xianLiu1(){
        return "success";
    }
}
