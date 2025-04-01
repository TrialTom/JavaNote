package com.cs.javaee.myclassloader;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author ：TrialCat
 * @description：自定义类加载器
 * @date ：2024/03/24 15:39
 */

public class MyClassLoader extends ClassLoader{
    String classPath;

    public MyClassLoader(String classPath) {
        this.classPath = classPath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        // 将class文件写入内存并用数组存储
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            FileInputStream in = new FileInputStream(classPath);
            int dataRead = 0;
            byte[] bytes = new byte[1024];
            while ((dataRead = in.read(bytes)) != -1){
                out.write(bytes, 0, dataRead);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // 用defineClass方法将数组转换为class字节码文件
        byte[] bytes01 = out.toByteArray();
        Class<?> aClass = defineClass(name, bytes01, 0, bytes01.length);
        return aClass;
    }
}
