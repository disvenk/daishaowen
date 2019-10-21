package com.daishaowen.test.shujukulianjiechi;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PooledConnection {

    //表示繁忙的标志，复用的标志，线程安全
    private boolean isBusy = true;

    //正真的管道，用来操作数据的java.sql.Connection
    private Connection connection;

    //构造方法，其它的应用程序调用它的时候，是要创建并且给它初始化组件
    public PooledConnection(Connection connection,boolean isBusy) {
        this.isBusy = isBusy;
        this.connection = connection;
    }

    //管道业务执行完毕，可以被其它线程再次调用
    public void close(){
        this.isBusy = false;
    }

    public ResultSet queryBySql(String sql){
        Statement statement=null;
        ResultSet rs = null;
        try{
            statement = connection.createStatement();
            rs = statement.executeQuery(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return rs;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean isBusy() {
        return isBusy;
    }

    public void setBusy(boolean busy) {
        isBusy = busy;
    }
}
