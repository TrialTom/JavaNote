package com.cs.javaee.homework.lambdatest.maptest;

/**
 * @author Lenovo
 */
public class Demo {
    public static void main(String[] args) {
        Student stu01 = new Student(1, 300);
        Student stu02 = new Student(2, 334);
        Student stu03 = new Student(3, 234);

        Student[] students = {stu01, stu02, stu03};
        Object[] arr = mapObjectArrByMap(students, o -> ((Student) o).stuID);
        for (Object ele : arr) {
            System.out.println(ele);
        }
        System.out.println("----------------");

        String[] strings = {"123", "djsal", "12313", "123kjkj4", "123k423"};
        Object[] arr1 = mapObjectArrByMap(strings, o -> ((String) o).length());
        for (Object ele : arr1) {
            System.out.println(ele);
        }


    }

    public static Object[] mapObjectArrByMap(Object[] o, IMap map) {
        if (o.length == 0) {
            return null;
        }
        Object[] arr = new Object[o.length];
        for (int i = 0; i < o.length; i++) {
            arr[i] = map.accept(o[i]);
        }
        return arr;
    }
}

class Student {
    int stuID;
    int grade;

    public Student(int a, int b) {
        this.stuID = a;
        this.grade = b;
    }
}

/**
 * 将一个对象数组，映射成另一个对象数组。
 * 比如：
 * 将一个Student对象数组，映射为装所有学生的成绩的数组。
 * 将一个String对象数组，映射为装所有字符串对象长度的数组。
 * .....
 * 所以这个映射的规则就是将一个Object对象转换为另一个Object对象，将这个规则应用到整个数组中，就完成了数组元素的映射。
 *
 * @since
 */

@FunctionalInterface
interface IMap {
    Object accept(Object o);
}