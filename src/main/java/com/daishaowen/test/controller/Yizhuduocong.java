package com.daishaowen.test.controller;

import com.daishaowen.test.yizhuduocong.TargetDataSource1;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by disvenk.dai on 2018-12-19 11:21
 */
@Controller
@RequestMapping("yizhuduocong")
public class Yizhuduocong {

    @RequestMapping("test1")
    @ResponseBody
    @TargetDataSource1(name="read")
    public String test1(){

        return "success";
    }
}
