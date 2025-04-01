package com.cs.util;

import com.mysql.jdbc.Driver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * @author ：TrialCat
 * @description：TODO
 * @date ：2024/04/28 9:33
 */

public class JDBCUtil {

    static String url;
    static String username;
    static String password;
    static String driver;

    // 静态代码块以加载配置文件
    static {
        try {
            FileInputStream in = new FileInputStream("jdbc.properties");
            Properties properties = new Properties();
            properties.load(in);

            url = properties.getProperty("url");
            username = properties.getProperty("username");
            password = properties.getProperty("password");
            driver = properties.getProperty("driver");
        } catch (IOException e) {
            System.out.println("配置文件读取失败!!!");
            throw new RuntimeException(e);
        }
    }

    /**
     * 建立与数据库的连接
     *
     * @return ： 返回连接对象
     */
    public static Connection getConnection() {
        Connection connection = null;
        try {

            // 注册
            Class.forName(driver);
            // 建立连接
            connection = DriverManager.getConnection(url, username, password);

        } catch (Exception e) {
            System.out.println("连接建立失败");
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 关闭各项连接
     *
     * @param connection：连接数据库
     * @param statement:传递sql语句的通道
     * @param resultSet:结果集
     */
    public static void close(Connection connection, Statement statement, ResultSet resultSet) {
        // 分先后次序
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            try {
                if (statement != null) {

                    statement.close();
                }

            } finally {
                connection.close();
            }
        } catch (Exception e) {
            System.out.println("断开失败!");
            e.printStackTrace();
        }
    }
}
