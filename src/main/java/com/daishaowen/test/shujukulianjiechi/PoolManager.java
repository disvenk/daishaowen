package com.daishaowen.test.shujukulianjiechi;

/*
* 内部内单例模式来对外界提供连接池对象
* 严格保证线程安全机制
* 懒汉式，饿汉式，双重if判断
* jvm 由于线程不安全形成两处实例对象 多占用服务器资源
* 两个实例判断后。合并返回一个实例
* */
public class PoolManager {

    private static class createPool{
        private static MyPoolImpl myPool = new MyPoolImpl();
    }

    /*
    * jvm当中加载器加载字节码是一个严格互斥理论
    * */
    public static MyPoolImpl getInstace(){
        return createPool.myPool;
    }
}
