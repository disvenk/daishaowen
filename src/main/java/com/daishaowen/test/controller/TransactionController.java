package com.daishaowen.test.controller;

import com.daishaowen.test.serviceImpl.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("transation")
@Controller
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @RequestMapping("/getNginx")
    @ResponseBody
    public String getNignx() throws InterruptedException {
        transactionService.get();
        return "sucess";
    }

    @RequestMapping("/getNginx2")
    @ResponseBody
    public String getNignx2() throws InterruptedException {
        transactionService.getNginx2();
        return "sucess2";
    }

}
