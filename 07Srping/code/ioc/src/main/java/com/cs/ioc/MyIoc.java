package com.cs.ioc;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author ：TrialCat
 * @description：TODO
 * @date ：2024/05/24 19:43
 */

public class MyIoc {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("application.xml");

    }
}
