package com.cs;

import com.cs.bean.Stu;
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
 * @date ：2024/05/04 11:49
 */

public class MybatisTest {

    static SqlSession sqlSession;
    static UseStudentTable useStudentTable;


    @BeforeClass
    public static void init() throws IOException {
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
        // 创建Session工厂
        SqlSessionFactory build = sqlSessionFactoryBuilder.build(resourceAsStream);
        // 获取Session true：自动提交事务
        sqlSession = build.openSession(true);
        // 反射实例化对象
        useStudentTable = sqlSession.getMapper(UseStudentTable.class);
    }

    @Test
    public void TestDemo01(){
        Stu stu = useStudentTable.checkStuById(8);

        System.out.println("stu = " + stu);
    }

    @AfterClass
    public static void over(){
        sqlSession.close();
    }
}
