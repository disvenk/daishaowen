package com.daishaowen.test.shijianqudong;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

/**
 * Created by disvenk.dai on 2018-12-03 10:59
 */
@Service
public class StartAddDataListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event)
    {
        if(event.getApplicationContext().getParent() == null){//root application context 没有parent，他就是老大.
            //需要执行的逻辑代码，当spring容器初始化完成后就会执行该方法。
            System.out.println("spring容器加载完毕，所有bean载入完成");
        }

        //或者下面这种方式
        if(event.getApplicationContext().getDisplayName().equals("Root WebApplicationContext"))
        {
            System.out.println("spring容器加载完毕2");
        }

    }
}
