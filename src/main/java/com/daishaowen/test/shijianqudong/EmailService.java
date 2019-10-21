package com.daishaowen.test.shijianqudong;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

/**
 * Created by disvenk.dai on 2018-09-12 17:35
 * 定义邮件服务，积分服务，其他服务(事件订阅者)
 */
@Service
public class EmailService  implements ApplicationListener<UserRegisterEvent> {

    //@EventListener使用该注解后不用实现ApplicationListener，且下面的方法名可以自定义，但是参数还是继承了ApplicationEvent的实现类
    @Override
    public void onApplicationEvent(UserRegisterEvent userRegisterEvent) {
        System.out.println("邮件服务接到通知，给 " + userRegisterEvent.getSource() + " 发送邮件...");
        throw new RuntimeException("邮件发送异常");
    }
}
