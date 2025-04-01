package com.cs.javaee.annotationpractice;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @author ：TrialCat
 * @description：注解练习
 * @date ：2024/03/25 9:28
 */

public class DemoTest {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException {
        // 注解获取的起点还是字节码文件
        Class<DemoTest> aClass = DemoTest.class;
        // 利用字节码文件找到使用过注解的方法或实例
        Method declaredMethod = aClass.getDeclaredMethod("func");
        // 判断方法对象是否使用了注解
        if(!declaredMethod.isAnnotationPresent(MyAnnotation.class)){
            return;
        }
        // 创建注解对象，获取注解属性值
        MyAnnotation annotation = declaredMethod.getAnnotation(MyAnnotation.class);
        System.out.println("annotation.maxSize() = " + annotation.maxSize());
        System.out.println("annotation.minSize() = " + annotation.minSize());
    }
    @MyAnnotation
    public void func(){

    }
}

/**
 * @author TrialCat
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface MyAnnotation{
    int minSize() default 100;
    int maxSize() default 1000;
}
