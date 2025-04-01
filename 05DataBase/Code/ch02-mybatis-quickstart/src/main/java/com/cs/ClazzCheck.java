package com.cs;

import com.cs.bean.Clazz;
import org.apache.ibatis.annotations.Param;

/**
 * @author TrialCat
 */
public interface ClazzCheck {
    /**
     * 查询数据库，并返回查找得到的结果
     * @return : 返回实例对象
     */
    Clazz checkById(Integer id);

    int updateById(@Param("id") Integer id,
                   @Param("clazz") Clazz clazz);
}
