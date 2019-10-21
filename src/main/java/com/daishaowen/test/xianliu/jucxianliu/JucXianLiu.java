package com.daishaowen.test.xianliu.jucxianliu;

import java.util.concurrent.Semaphore;

public class JucXianLiu {

    Semaphore semaphore = new Semaphore(50);

    public void xianliu(){
        //先查询缓存，如果缓存命中直接返回结果
        //select cache

        //在查询数据库之前进行限流控制,尝试获取许可，失败后等待2秒
        semaphore.tryAcquire(2000);

        //再次查询缓存，可能其它线程已经将数据写入了缓存
        //select cache

        //查询数据库，将数据插入缓存
        //select db
        //insert cache

        //返回结果
        return;

    }
}
