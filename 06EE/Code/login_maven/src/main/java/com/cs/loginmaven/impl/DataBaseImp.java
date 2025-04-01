package com.cs.loginmaven.impl;

import com.cs.loginmaven.InsertMapper;
import com.cs.loginmaven.bean.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;


/**
 * @author ：TrialCat
 * @description：数据库
 * @date ：2024/05/16 12:10
 */

public class DataBaseImp {
    static SqlSession sqlSession;
    static InsertMapper insertMapper;

    static {
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        // 读取配置文件
        InputStream resourceAsStream =
                DataBaseImp.class.getClassLoader().getResourceAsStream("mybatis-config.xml");
        // 传入配置文件
        SqlSessionFactory build = sqlSessionFactoryBuilder.build(resourceAsStream);
        // 获取sqlSession实例
        sqlSession = build.openSession(true);
        // 创建Mapper实例
        insertMapper = sqlSession.getMapper(InsertMapper.class);

    }

    /**
     * 将有效数据插入到数据库
     * @param user ： 需要插入的数据
     * @return ： 返回影响行数
     */
    public static Integer insertUser(User user) {
        Integer integer = insertMapper.insertUser(user);
        return integer;
    }

    public static String checkNameByID(int id){
        return insertMapper.checkNameByID(1);
    }
}
