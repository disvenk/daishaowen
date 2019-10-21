package com.daishaowen.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/exception")
public class ExceptionController {

    @GetMapping(value = "/error")
    @ResponseBody
    public String exception(HttpServletRequest request,
                            HttpServletResponse response, ModelMap modelMap){

        //int i = 1/0;
        return "yes";

    }

    @GetMapping(value = "/error1")
    @ResponseBody
    public String exception1(HttpServletRequest request,
                            HttpServletResponse response, ModelMap modelMap){
        System.out.println("zouleajax");
        //int i = 1/0;
        return "no";
    }
}
