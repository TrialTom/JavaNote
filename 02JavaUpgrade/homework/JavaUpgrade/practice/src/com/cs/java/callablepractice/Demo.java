package com.cs.java.callablepractice;

import java.io.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author ：TrialCat
 * @description：Callalbe方式去实现多线程文件复制
 * @date ：2024/03/25 15:48
 */

public class Demo {
    public static void main(String[] args) throws ExecutionException, InterruptedException, IOException {
        // 创建FutureTask对象，不使用线程池
        FutureTask<String> task01 = new FutureTask<>(new CopyFileCallable
                ("C:\\Users\\Lenovo\\Pictures\\Screenshots\\02test.jpg", "copy01.jpg"));
        FutureTask<String> task02 = new FutureTask<>(new CopyFileCallable
                ("C:\\Users\\Lenovo\\Pictures\\Screenshots\\03test.jpeg", "copy02.jpeg"));
        FutureTask<String> task03 = new FutureTask<>(new CopyFileCallable
                ("C:\\Users\\Lenovo\\Pictures\\Screenshots\\04test.jpg", "copy03.jpg"));

        // 加入线程
        new Thread(task01).start();
        new Thread(task02).start();
        new Thread(task03).start();

        String s1 = task01.get();
        String s2 = task02.get();
        String s3 = task03.get();

        OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream("pathname.text"));
        out.write(s1);
        out.write(System.lineSeparator());
        out.write(s2);
        out.write(System.lineSeparator());
        out.write(s3);
        out.close();
    }
}

class CopyFileCallable implements Callable<String> {
    File oldFile;
    File newFile;

    public CopyFileCallable(String oldFile, String newFile) {
        this.oldFile = new File(oldFile);
        this.newFile = new File(newFile);
    }

    @Override
    public String call() throws Exception {
        FileInputStream in = new FileInputStream(oldFile);
        FileOutputStream out = new FileOutputStream(newFile);

        int readData;
        byte[] bytes = new byte[1024];
        while ((readData = in.read(bytes)) != -1) {
            out.write(bytes, 0, readData);
        }
        return newFile.getAbsolutePath();
    }
}