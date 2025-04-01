package com.cs;

import com.cs.bean.Clazz;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

/**
 * @author ：TrialCat
 * @description：查询语句
 * @date ：2024/05/02 10:43
 */

public class MybatisTest {
    public static void main(String[] args) {
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        InputStream inputStream = MybatisTest.class.getClassLoader().getResourceAsStream("mybatis-config.xml");
        // 创建工厂
        SqlSessionFactory factory = sqlSessionFactoryBuilder.build(inputStream);

        // 获取SqlSession实例
        SqlSession sqlSession = factory.openSession();
        // 传输sql语句
        Clazz clazz = sqlSession.selectOne("space.aaa", 3);

        System.out.println("clazz = " + clazz);

        // int insert = sqlSession.insert("space.bbb", new Clazz(8, "八班"));
        //
        // System.out.println("insert = " + insert);

        int delete = sqlSession.delete("space.deleteColumn", 8);
        System.out.println("delete = " + delete);

        sqlSession.commit();

        sqlSession.close();
    }
}
