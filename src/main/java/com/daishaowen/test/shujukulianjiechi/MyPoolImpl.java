package com.daishaowen.test.shujukulianjiechi;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.CopyOnWriteArrayList;

public class MyPoolImpl implements IMyPool {

    //spring源码当中所有配置都会对应源码对象
    private static String driver = null;

    private static String url = null;

    private static String user = null;

    private static String password = null;

    private static int initCount = 3;

    private static int stepSize = 10;

    private static int poolMaxSize = 150;

    private static CopyOnWriteArrayList<PooledConnection> pooledConnections = new CopyOnWriteArrayList<>();

    public MyPoolImpl(){
        //分层设计
        init();
    }

    //初始化参数在外面的config
    public void init(){
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("jdbcPool.properties");
        Properties properties = new Properties();
        try{
            properties.load(inputStream);
        }catch (IOException e){
            e.printStackTrace();
        }

        driver = properties.getProperty("jdbcDriver");
        url = properties.getProperty("url");
        user = properties.getProperty("userName");
        password = properties.getProperty("password");

        if(Integer.parseInt(properties.getProperty("initCount"))>0){
            initCount = Integer.parseInt(properties.getProperty("initCount"));
        }

        if(Integer.parseInt(properties.getProperty("stepSize"))>0){
            stepSize = Integer.parseInt(properties.getProperty("stepSize"));
        }

        if(Integer.parseInt(properties.getProperty("poolMaxSize"))>0){
            poolMaxSize = Integer.parseInt(properties.getProperty("poolMaxSize"));
        }

        try {
            Driver dbDriver = (Driver) Class.forName(driver).newInstance();
            DriverManager.registerDriver(dbDriver);
        }catch (Exception e){
            e.printStackTrace();
        }

        //初始化线程池
        createConnection(initCount);

    }


    @Override
    public PooledConnection getPooledConnection() {
        if(pooledConnections.size()==0){
            System.out.println("数据库连接池没有正常初始化，将启用补刀机制再次初始化");
            createConnection(initCount);
        }

        //获取管道资源1、没有被占用 2、管道处于长期等的时候有可能被gc
        PooledConnection pooledConnection = getRealConnection();
        //不用if的原因是有n多个线程会进入方法，分配时间片，可能产生假死
        //线程醒过来可能直接带上null就往下走了
        while (pooledConnection==null){
            //管道数量不够要扩容
            createConnection(stepSize);
            pooledConnection = getRealConnection();
            //为了保证上层调用的对象不出现问题
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return pooledConnection;
    }

    //1、没有被占用 2、管道处于长期等的时候有可能被gc
    private synchronized PooledConnection getRealConnection(){
        for(PooledConnection conn : pooledConnections){
            //线程是否被占用
            if(!conn.isBusy()){
                Connection connection = conn.getConnection();
                try {
                    //判断连接是否超时
                    if(connection.isValid(2000)){
                        //替换conn对象底层java.sql.Connection
                        //直接使用最高效率的底层替换
                        connection = DriverManager.getConnection(url, user, password);
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }
                //线程不会去争夺资源
                conn.setBusy(true);
                return conn;
            }
        }
        //如果返回null，则会在上层方法中while方法中步进量扩容
        return null;
    }

    @Override
    public void createConnection(int count) {
        if(poolMaxSize>0 && pooledConnections.size()+count<=poolMaxSize){
            for(int i=0;i<count;i++){
                try {
                    Connection connection = DriverManager.getConnection(url, user, password);
                    PooledConnection pooledConnection = new PooledConnection(connection,false);
                    //加入高并发安全集合类
                    pooledConnections.add(pooledConnection);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }else {
            new RuntimeException("创建管道参数非法");
        }
    }
}
