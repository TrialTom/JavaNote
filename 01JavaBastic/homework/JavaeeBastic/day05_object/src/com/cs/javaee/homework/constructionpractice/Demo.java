package com.cs.javaee.homework.constructionpractice;

/**
 * @author Lenovo
 */
public class Demo {
    public static void main(String[] args) {

    }
}

class Teacher{
    String name;
    int age;
    String gender;
    int teacherId;

    public Teacher() {
    }

    public Teacher(String name) {
        this.name = name;
    }

    public Teacher(String name, String gender) {
        this.name = name;
        this.gender = gender;
    }

    public Teacher(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }


    public Teacher(String name, int age, String gender, int teacherId) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.teacherId = teacherId;
    }

    public void print(){
        System.out.println(this.name+","+this.age+","+this.gender+","+this.teacherId);
    }
}