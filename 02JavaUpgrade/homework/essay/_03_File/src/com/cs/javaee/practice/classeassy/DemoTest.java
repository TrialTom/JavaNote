package com.cs.javaee.practice.classeassy;


import org.junit.Test;

import java.io.*;

/**
 * @author ：TrialCat
 * @description：TODO
 * @date ：2024/03/07 9:05
 */

public class DemoTest {
    @Test
    public void test01() throws IOException {
        File file = new File("b\\b.txt");
        System.out.println("file.createNewFile() = " + file.createNewFile());
        FileOutputStream out = new FileOutputStream(file,true);
        out.write("\r\n12312341".getBytes());
    }

    @Test
    public void test02() throws IOException {
        FileInputStream file1 = new FileInputStream("b\\b.txt");
        byte[] b = new byte[3];
        int readDate;
        while ((readDate = file1.read(b)) != -1){
            System.out.println(new String(b));
        }
        file1.close();
    }

    @Test
    public void test03() throws IOException {
        FileOutputStream file1 = new FileOutputStream("b\\b.txt");

        byte[] b = {97, 98, 99};
        for (int i = 0; i < 3; i++) {
            file1.write(b);
            System.out.println("i = " + i);
        }
        file1.close();
    }
}
