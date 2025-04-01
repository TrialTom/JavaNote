package com.cs.javaee.homework.clonetest;

/**
 * @author ：TrialCat
 * @description：TODO
 * @date ：2024/02/28 16:32
 */

public class Demo {
    public static void main(String[] args) throws CloneNotSupportedException {
        Teacher teacher = new Teacher(20, "杨老师", new Student(17, "xxx", new Star(40, "韩寒")));

        Teacher teacherClone = (Teacher) teacher.clone();
        teacherClone.stu = ((Student) teacher.stu.clone());
        teacherClone.stu.s = ((Star) teacher.stu.s.clone());
        teacherClone.stu.age = 100;
        teacherClone.stu.s.name = "林尼";
        System.out.println(teacher.toString());
        System.out.println(teacherClone.toString());

    }
}

/**
 * create by: TrialCat
 * description:
 * 分别定义以下类：
 * 教师类Teacher
 * 属性：int age;String name;Student stu
 * 学生类Student
 * 属性：int age；String name；Star s
 * 明星类Star
 * 属性：int age，String name
 * 尝试完成Teacher对象的深度克隆，并写代码进行测试
 * create time: 2024/2/28 16:32
 *
 * @param
 * @return
 */

class Teacher implements Cloneable {
    int age;
    String name;
    Student stu;

    public Teacher(int age, String name, Student stu) {
        this.age = age;
        this.name = name;
        this.stu = stu;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", stu=" + stu.toString() +
                '}';
    }
}

class Student implements Cloneable {
    int age;
    String name;
    Star s;

    public Student(int age, String name, Star s) {
        this.age = age;
        this.name = name;
        this.s = s;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", s=" + s.toString() +
                '}';
    }
}

class Star implements Cloneable {
    int age;
    String name;

    public Star() {
    }

    public Star(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "Star{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}