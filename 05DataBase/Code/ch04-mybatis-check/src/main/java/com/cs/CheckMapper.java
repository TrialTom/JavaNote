package com.cs;

import com.cs.bean.Clazz;
import org.apache.ibatis.annotations.Param;

/**
 * @author ：TrialCat
 * @description：TODO
 * @date ：2024/05/05 11:14
 */

interface CheckMapper {

    /**
     * 分次查询
     * 通过班级名字来查找班级中的学生
     *
     * @param id :班级的编号
     * @return ： 返回学生列表
     */
    Clazz selectStuByName(@Param("id") Integer id);

    /**
     * 连接查询
     * @param id ：需要查找班级的编号
     * @return ： 返回班级
     */
    Clazz selectStuByName01(@Param("id") Integer id);
}
