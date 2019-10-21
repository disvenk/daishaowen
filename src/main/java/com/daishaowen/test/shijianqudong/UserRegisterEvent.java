package com.daishaowen.test.shijianqudong;

import org.springframework.context.ApplicationEvent;

/**
 * Created by disvenk.dai on 2018-09-12 17:33
 * 定义用户注册事件
 */
public class UserRegisterEvent extends ApplicationEvent {
    public UserRegisterEvent(String name) { //name即source
        super(name);
    }
}
