package com.cs.javaee.practice.morkprintln;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author ：TrialCat
 * @description：TODO
 * @date ：2024/03/10 15:08
 */

public class DemoTest {

    /**
     * 利用System.in完成Scanner的nextLine()的功能
     */
    @Test
    public void test01() throws IOException {
        // 创建流
        InputStream in = System.in;
        InputStreamReader inputStreamReader = new InputStreamReader(in);
        BufferedReader buffReader = new BufferedReader(inputStreamReader);

        String s = buffReader.readLine();
        System.out.println(s);
        // 关闭流
        buffReader.close();
        inputStreamReader.close();
    }

    /**
     * 循环读取
     */
    @Test
    public void test02() throws IOException {
        // 创建
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while ((s = in.readLine()) != System.lineSeparator()) {
            System.out.println(s);
        }
        // 关闭
        in.close();
    }
}
