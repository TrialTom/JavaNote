package com.cs;

import com.cs.bean.Clazz;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author ：TrialCat
 * @description：TODO
 * @date ：2024/05/02 20:35
 */

public class MybatisMain {

    static SqlSession sqlSession = null;

    static ClazzCheck mapper = null;

    @BeforeClass
    public static void init() throws IOException {
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();

        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");

        SqlSessionFactory sqlSessionFactory = builder.build(inputStream);

        sqlSession = sqlSessionFactory.openSession(true);

        mapper = sqlSession.getMapper(ClazzCheck.class);

    }

    @Test
    public void TestDemo01() {

        Clazz clazz = mapper.checkById(6);

        System.out.println("clazz = " + clazz);
    }

    @Test
    public void TestUpdate(){
        int byId = mapper.updateById(1, new Clazz(1, "暂无"));

        System.out.println("byId = " + byId);
    }

    @AfterClass
    public static void over(){
        sqlSession.close();
    }
}
