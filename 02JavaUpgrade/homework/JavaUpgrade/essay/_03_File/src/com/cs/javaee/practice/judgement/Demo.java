package com.cs.javaee.practice.judgement;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * @author ：TrialCat
 * @description：判断某个目录下有没有a.jpg文件
 * @date ：2024/03/06 10:56
 */

public class Demo {
    @Test
    public void test01() throws IOException {
        String property = System.getProperty("user.dir");
        System.out.println("property = " + property);
        // 指定目标文件
        String s = "a.jpg";
        // 目标文件所在目录
        File fileDest = new File("a");
        File file = new File(fileDest, s);

        if(file.isFile()){
            System.out.println("存在");
            return;
        }
        System.out.println("no");
    }
}
