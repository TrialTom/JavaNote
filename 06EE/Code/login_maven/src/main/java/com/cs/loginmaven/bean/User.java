package com.cs.loginmaven.bean;

import lombok.Data;

/**
 * @author ：TrialCat
 * @description：用户实体类
 * @date ：2024/05/15 20:53
 */
@Data
public class User {
    private String username;
    private String email;
    private String password;
    private String confirmPassword;
    private String fileName;
}
