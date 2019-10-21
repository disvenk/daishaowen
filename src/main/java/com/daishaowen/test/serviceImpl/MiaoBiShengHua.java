package com.daishaowen.test.serviceImpl;

import com.daishaowen.test.miaobishenghua.DoSomething;
import com.daishaowen.test.miaobishenghua.Tuser;
import org.springframework.stereotype.Service;

@Service
public class MiaoBiShengHua {
    @DoSomething(key = "#user.Id",cacheName = "d")
    public void doSomething(Tuser user){
        System.out.println("执行了正常逻辑");
    }
}
