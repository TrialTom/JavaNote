package com.cs.javaee.designpractice;

import java.io.*;

/**
 * @author ：TrialCat
 * @description：练习
 * @date ：2024/03/28 13:33
 * <p>
 * 具体要求：利用装饰设计模式思想，以及BufferedInputStream，设计一个类LowerCaseInputStream，修饰BufferedInputStream
 * 实现以下功能：拓展其功能，将读取到的英文字符大写的全部转换为小写。
 */

public class DemoTest {
    public static void main(String[] args) throws IOException {
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("a.txt"));
        out.write("AbcDEFGhIJKLMNOPQ".getBytes());
        out.close();

        BufferedInputStream in = new BufferedInputStream(new FileInputStream("a.txt"));

        LowerCaseInputStream lowerIn = new LowerCaseInputStream(in);
        byte[] bytes = new byte[10];
        int readData = 0;
        // while ((readData = in.read(bytes)) != -1) {
        //     System.out.print(new String(bytes, 0, readData));
        // }
        while ((readData = lowerIn.read(bytes)) != -1) {
            System.out.print(new String(bytes, 0, readData));
        }
    }
}

class LowerCaseInputStream extends BufferedInputStream {

    static boolean tag = true;
    InputStream in;

    public LowerCaseInputStream(BufferedInputStream in) {
        super(in);
        this.in = in;
    }

    @Override
    public synchronized int read() throws IOException {
        int readData = in.read();
        if (readData >= 65 && readData <= 90) {
            return readData += 32;
        }
        return readData;
    }

    @Override
    public synchronized int read(byte[] b) throws IOException {
        int readData;
        int i = 0;
        while ((readData = in.read()) != -1) {
            if (readData >= 65 && readData <= 90) {
                readData += 32;
            }
            b[i++] = (byte) readData;
            if (i == b.length) {
                return i;
            }
        }
        if (tag) {
            tag = false;
            return i--;
        }
        return -1;
    }
}
