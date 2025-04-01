package com.cs.javaee.practice.classeassy;

import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author ：TrialCat
 * @description：TODO
 * @date ：2024/03/10 8:53
 */

public class Demo01Test {
    @Test
    public void test01() throws IOException {
        FileOutputStream out = new FileOutputStream("b\\b.txt");
        out.write("1000".getBytes());
        out.close();
    }

    @Test
    public void test02(){
        File file = new File("b\\b.text");
        file.delete();
    }


}
