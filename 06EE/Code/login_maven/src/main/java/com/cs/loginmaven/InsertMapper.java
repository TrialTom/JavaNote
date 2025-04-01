package com.cs.loginmaven;

import com.cs.loginmaven.bean.User;
import org.apache.ibatis.annotations.Param;


/**
 * @author ：TrialCat
 * @description：TODO
 * @date ：2024/05/16 11:31
 */

public interface InsertMapper {
    /**
     * 将用户信息写入数据库
     * @param user : 用户对象
     * @return ： 返回影响行
     */
    Integer insertUser(@Param("user") User user);

    String checkNameByID(@Param("id") Integer id);
}
