package com.daishaowen.test.devTest;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Test1 {

    public static void main(String[] args) throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        //建立数据库对象
        Connection conn  = DriverManager.getConnection("jdbc:mysql://192.168.0.120:3306/log_analysis?useUnicode=true&characterEncoding=utf8","root","123456");
        //建立操作对象
        conn.setAutoCommit(false);
        Statement stmt= conn.createStatement();
        stmt.setQueryTimeout(20);
        //结果集
        boolean execute = stmt.execute("update nginx_logs set request_type='DELETE' where id=171547");
        conn.commit();
        if(stmt!=null){
            stmt.close();
        }
        if(conn!=null) {
            conn.close();
        }
    }
}
