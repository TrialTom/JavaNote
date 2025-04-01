package com.cs.javaee.practice.copyphoto;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author ：TrialCat
 * @description：TODO
 * @date ：2024/03/07 11:29
 */

public class DemoTest {
    @Test
    public void test01() throws IOException {
        System.out.println("System.getProperty(\"user.dir\") = " + System.getProperty("user.dir"));

        File photo = new File("C:\\Users\\Lenovo\\Pictures\\Screenshots\\01.png");
        FileInputStream copyPhoto = new FileInputStream(photo);

        File destFile = new File("copy.png");
        FileOutputStream destPhoto = new FileOutputStream(destFile);

        int readDate;
        // 多字节读取
        byte[] b = new byte[1023];
        while ((readDate = copyPhoto.read(b)) != -1) {
            destPhoto.write(b, 0, readDate);
        }
        // 资源回收
        copyPhoto.close();
        destPhoto.close();
        System.out.println("Over");
    }
}
