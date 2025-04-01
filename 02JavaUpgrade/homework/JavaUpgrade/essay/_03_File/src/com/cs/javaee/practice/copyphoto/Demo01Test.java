package com.cs.javaee.practice.copyphoto;

import org.junit.Test;

import java.io.*;

/**
 * @author ：TrialCat
 * @description：TODO
 * @date ：2024/03/09 9:19
 */

public class Demo01Test {
    @Test
    public void test01() throws IOException {
        // 字符流的复制
        // 创建对象
        InputStreamReader in = new InputStreamReader(new FileInputStream("copy.png"));

        OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream("a\\a.png"));
        long start = System.currentTimeMillis();
        // 复制
        copy01(in, out);

        // copy02(in, out);

        long result = System.currentTimeMillis();
        System.out.println("result-start = " + (result - start));
        // 关闭流
        in.close();
        out.close();
    }

    private void copy02(InputStreamReader in, OutputStreamWriter out) throws IOException {
        int readDate;
        while ((readDate = in.read()) != -1) {
            out.write(readDate);
        }
    }

    private void copy01(InputStreamReader in, OutputStreamWriter out) throws IOException {
        char[] chars = new char[1024];
        int readDate;
        while ((readDate = in.read(chars)) != -1) {
            out.write(chars, 0, readDate);
        }
    }
}
