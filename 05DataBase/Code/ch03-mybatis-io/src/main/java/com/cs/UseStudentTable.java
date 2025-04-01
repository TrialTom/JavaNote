package com.cs;

import com.cs.bean.Stu;
import org.apache.ibatis.annotations.Param;

/**
 * @author TrialCat
 */
public interface UseStudentTable {

    /**
     * 通过id值来查找目标对象
     * @param id 传入目标对象的id
     * @return ：返回目标对象的数据
     */
    Stu checkStuById(@Param("id") Integer id);
}
