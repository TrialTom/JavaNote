package com.cs.dbutilsuse.connectionpool;



import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author ：TrialCat
 * @description：TODO
 * @date ：2024/04/29 21:56
 */

public class DruidPool {
    static DataSource dataSource;

    static {
        try{
            FileInputStream fileInputStream = new FileInputStream("druid.properties");
            Properties properties = new Properties();
            properties.load(fileInputStream);
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接池
     * @return : 返回数据库连接池
     */
    public static DataSource getDataSource(){
        return dataSource;
    }

    /**
     * 获取连接
     * @return ： 返回连接
     */
    public static Connection getConnection(){
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return connection;
    }
}
