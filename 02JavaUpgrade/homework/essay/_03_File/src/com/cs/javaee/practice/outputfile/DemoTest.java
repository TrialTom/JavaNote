package com.cs.javaee.practice.outputfile;

import org.junit.Test;

import java.io.File;

/**
 * @author ：TrialCat
 * @description：递归输出某个目录下所有的的java文件
 * @date ：2024/03/06 10:58
 */

public class DemoTest {
    @Test
    public void test01() {
        String property = System.getProperty("user.dir");
        System.out.println("property = " + property);

        File file = new File("E:\\javaEE program\\02JavaUpgrade\\homework\\essay\\_03_File");
        // 后缀文件
        String s = "jpg";

        check(file, s);
    }

    public void check(File file, String s) {
        File[] files = file.listFiles();
        assert files != null;
        if (files.length == 0) {
            return;
        }
        for (File f : files) {
            if (f.isDirectory()) {
                check(f, s);
            }
            if (f.toString().endsWith(s)) {
                System.out.println(f);
            }
        }
    }
}
