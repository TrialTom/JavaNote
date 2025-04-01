package com.cs.javaee.homework.getclasstest;

/**
 * @author ：TrialCat
 * @description：TODO
 * @date ：2024/02/28 14:59
 */

public class Demo {
    public static void main(String[] args) {
        Student student = new Student();
        Teacher teacher = new Teacher();
        Class stu = student.getClass();
        Class teacher01 = teacher.getClass();
        System.out.println(stu == teacher01);
    }
}

/**
 * create by: TrialCat
 * description: 定义两个类，然后分别创建对象，调用getClass方法
 * 用“==”号比较它们的运行时Class对象是否相等，并说明原因
 * 理解运行时类对象、类加载、类的对象的区别
 * create time: 2024/2/28 15:24
 *
 * @param
 * @return
 */
class Student {
    int stuId;
    String name;
}

class Teacher {
    int tetherId;
    String name;

}