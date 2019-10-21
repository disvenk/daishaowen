package com.daishaowen.test.shujukulianjiechi;

/*
* 抽取连接池架构接口，这个类相当于java中的DataSource接口
* */
public interface IMyPool {
    //获取物理连接
    PooledConnection getPooledConnection();
    //创建连接
    void createConnection(int count);
}
