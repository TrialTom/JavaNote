package com.cs.jdbc;

import com.cs.util.JDBCUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author ：TrialCat
 * @description：测试
 * @date ：2024/04/28 9:06
 */

public class Demo01 {
    static ResultSet resultSet = null;

    public static void main(String[] args) throws SQLException {
        // 获取连接
        Connection connection = JDBCUtil.getConnection();

        Statement statement = connection.createStatement();

        // 插入
        // int affectRow = statement.executeUpdate("alter table clazz character set utf8 collate utf8_bin");
        //
        // System.out.println("affectRow = " + affectRow);

        resultSet = statement.executeQuery("select * from clazz where c_id = 1");
        resultSet.next();

        String c_name = resultSet.getString("c_name");

        System.out.println("c_name = " + c_name);

        JDBCUtil.close(connection, statement, resultSet);

    }
}
