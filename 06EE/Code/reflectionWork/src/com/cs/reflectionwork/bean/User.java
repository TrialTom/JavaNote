package com.cs.reflectionwork.bean;

import java.util.Arrays;

/**
 * @author ：TrialCat
 * @description：实体类
 * @date ：2024/05/10 11:37
 */

public class User {

    private String username;
    private String password;
    private String gender;
    private String[] course;

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String[] getCourse() {
        return course;
    }

    public void setCourse(String[] course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", gender='" + gender + '\'' +
                ", course=" + Arrays.toString(course) +
                '}';
    }
}
