package com.cs.loginmaven;

import com.cs.loginmaven.bean.User;
import com.cs.loginmaven.impl.DataBaseImp;
import org.junit.Test;

/**
 * @author ：TrialCat
 * @description：TODO
 * @date ：2024/05/16 15:27
 */

public class DemoTest {

    @Test
    public void Test01(){
        User user = new User();
        user.setUsername("ls");
        user.setPassword("123");
        user.setConfirmPassword("123");
        user.setEmail("1234");
        user.setFileName("1234");

        System.out.println("DataBaseImp.insertUser(1) = " + DataBaseImp.checkNameByID(1));

        // Integer integer = DataBaseImp.insertUser(user);
        // System.out.println("integer = " + integer);
    }
}
