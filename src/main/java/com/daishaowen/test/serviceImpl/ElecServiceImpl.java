package com.daishaowen.test.serviceImpl;

import com.daishaowen.test.mapper.ElectronicTicketConfigMapper;
import com.daishaowen.test.mapper.WechatConfigMapper;
import com.daishaowen.test.model.ElectronicTicketConfig;
import com.daishaowen.test.model.WechatConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ElecServiceImpl {

    @Autowired
    ElectronicTicketConfigMapper electronicTicketConfigMapper;

    @Autowired
    WechatConfigMapper wechatConfigMapper;

    public void update(ElectronicTicketConfig electronicTicket){
        electronicTicketConfigMapper.updateByPrimaryKeySelective(electronicTicket);
    }

    public void update(WechatConfig wechatConfig){
        wechatConfigMapper.updateByPrimaryKeySelective(wechatConfig);
    }

}
