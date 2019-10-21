package com.daishaowen.test.shejimoshi.templateModel.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by disvenk.dai on 2018-11-06 17:42
 * 抽象查询父类
 */
public abstract class AbstractDao {
    /**
     * 查询
     * 获得连接和执行sql的公共方法抽取在父类中
     * @param sql
     * @param params
     * @return
     */
    protected Object find(String sql, Object[] params) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Object obj = null;
        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                obj = rowMapper(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.free(rs, ps, conn);
        }
        return obj;
    }

    //sql执行后接下来怎么处理返回数据的业务放在具体的子类中去实现
    protected abstract Object rowMapper(ResultSet rs) throws SQLException;

    //同时可以添加 insert ，update 等方法
}
