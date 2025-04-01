package com.cs.javaee.annotationpractice01;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;

/**
 * @author ：TrialCat
 * @description：注解练习
 * @date ：2024/03/25 10:29
 */

public class DemoTest {
    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        // 利用学生工厂创建对象
        Student student = StudentFactory.createStudentObject("hh", 20);
        System.out.println(student);
    }
}

/**
 * create by: TrialCat
 * description: 限制ID长度和Age大小
 * create time: 2024/3/25 10:30
 */
class Student{
    @IdLimit
    String id;
   @AgeLimit
    int age;

    private Student(String id, int age) {
        this.id = id;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", age=" + age +
                '}';
    }
}

/**
 * @author TrialCat
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@interface IdLimit{
    int maxLimit() default 4;
}

/**
 * @author TrialCat
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@interface AgeLimit{
    int minLimit() default 18;
    int maxLimit() default 24;
}
