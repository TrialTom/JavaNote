package com.cs.javaee.homework.banpractice;

/**
 * @author Lenovo
 */
public class Demo {
    public static void main(String[] args) {
        Student s1 = Student.creatObject();
        System.out.println(Student.getCount());
        Student s2 = Student.creatObject();

    }
}

class Student {

    /*
    需求：
    定义一个Student类，并要求在其他类中，最多只能创建10个Student类的对象。
	分析：
		1，如果允许外部直接创建对象，显然无法控制创建对象的个数
		2，需要计数器指示外部创建对象的个数
     */
    private static int count = 0;

    public static int getCount() {
        return count;
    }

    public static Student creatObject() {
        if (Student.count < 2) {
            Student.count++;
            System.out.println("创建成功！");
            return new Student();
        }
        System.out.println("创建失败!");
        return null;
    }

    private Student() {

    }
}
