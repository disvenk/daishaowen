package com.daishaowen.test.controller;

import com.daishaowen.test.model.ElectronicTicketConfig;
import com.daishaowen.test.model.WechatConfig;
import com.daishaowen.test.serviceImpl.ElecServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ElecController {

    @Autowired
    ElecServiceImpl elecService;

    @RequestMapping("updateElec")
    public String updateElec(Long id,String app){
        ElectronicTicketConfig electronicTicketConfig = new ElectronicTicketConfig();
        electronicTicketConfig.setId(id);
        electronicTicketConfig.setAppsecret(app);
        elecService.update(electronicTicketConfig);
        return "updateElecchengong";
    }

    @RequestMapping("updateWchat")
    public String updateWchat(String id,String app){
        WechatConfig wechatConfig = new WechatConfig();
        wechatConfig.setId(id);
        wechatConfig.setAppsecret(app);
        elecService.update(wechatConfig);
        return "updateWchatchengong";
    }
}
