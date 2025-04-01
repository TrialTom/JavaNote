package com.cs.javaee.myclassloader;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author ：TrialCat
 * @description：测试
 * @date ：2024/03/24 15:50
 */

public class DemoTest {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        String classPath = "E:\\Log.class";
        MyClassLoader myClassLoader = new MyClassLoader(classPath);
        // 提出字节码文件，查找构造方法
        Class<?> aClass = myClassLoader.findClass("Log");
        Constructor<?> declaredConstructor = aClass.getDeclaredConstructor();
        // 创建对象
        Object o = declaredConstructor.newInstance();
        // 查找方法
        Method fun = aClass.getDeclaredMethod("fun");
        // 调用方法
        fun.invoke(o);

    }
}
