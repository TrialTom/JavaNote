package com.cs;

import com.cs.bean.Clazz;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author ：TrialCat
 * @description：TODO
 * @date ：2024/05/05 11:44
 */

public class TestCheckDemo1 {

    static SqlSession sqlSession = null;
    static CheckMapper checkMapper;

    @BeforeClass
    public static void init() throws IOException {
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
        // 传入配置文件
        SqlSessionFactory build = sqlSessionFactoryBuilder.build(resourceAsStream);
        // 获取SqlSession实例
        sqlSession = build.openSession(true);
        // 创建Mapper实例
        checkMapper = sqlSession.getMapper(CheckMapper.class);
    }

    @Test
    public void TestDemo1(){
        Clazz stus = checkMapper.selectStuByName(2);
        System.out.println("stus = " + stus);
    }

    @Test
    public void TestDemo02(){
        Clazz stuList = checkMapper.selectStuByName01(2);
        System.out.println("stuList = " + stuList);
    }
}
