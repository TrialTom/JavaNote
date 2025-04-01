package com.cs.javaee.annotationpractice01;

import java.io.FileOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * @author ：TrialCat
 * @description：创建学生对象
 * @date ：2024/03/25 10:35
 */

public class StudentFactory {
    /**
     * create by: TrialCat
     * description: 用一个方法创建student对象
     * create time: 2024/3/25 10:38
     *
      * @return Student
     */
    public static Student createStudentObject(String id, int age) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
    // 使用映射创建对象并赋值
        Class<?> aClass = Class.forName("com.cs.javaee.annotationpractice01.Student");
        Field idField = aClass.getDeclaredField("id");
        Field ageField = aClass.getDeclaredField("age");
        // 判断是否使用注解
        boolean isIdAnnotation = idField.isAnnotationPresent(IdLimit.class);
        boolean isAgeAnnotation = ageField.isAnnotationPresent(AgeLimit.class);
        if(! isIdAnnotation || !isAgeAnnotation){
            System.out.println(isIdAnnotation);
            System.out.println(isAgeAnnotation);
            System.out.println("未使用限制");
            return null;
        }
        // 根据注解所给的信息限制成员变量的赋值
        IdLimit idLimit = idField.getAnnotation(IdLimit.class);
        AgeLimit ageLimit = ageField.getAnnotation(AgeLimit.class);
        if(id != null && idLimit.maxLimit() >= id.length()){
            if(age >= ageLimit.minLimit() && age <= ageLimit.maxLimit()){
                // 创建对象并赋值
                Constructor<?> declaredConstructor = aClass.getDeclaredConstructor(String.class, int.class);
                declaredConstructor.setAccessible(true);
                Object o = declaredConstructor.newInstance(id, age);
                return ((Student) o);
            }
            throw new IllegalArgumentException("年龄不符合规范！");
        }
        throw new IllegalArgumentException("id不符合规范！");
    }
}
