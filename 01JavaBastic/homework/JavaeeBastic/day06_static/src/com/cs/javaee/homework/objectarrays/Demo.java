package com.cs.javaee.homework.objectarrays;

/**
 * @author Lenovo
 */
public class Demo {
    public static void main(String[] args) {
        Student s1 = new Student("张三", "男", 80.1);
        Student s2 = new Student("李四", "男", 90.1);
        Student s3 = new Student("王五", "男", 70.1);
        Student s4 = new Student("赵六", "男", 80.1);
        Student s5 = new Student("刘七", "男", 95.1);

        Student[] arr = {s1, s2, s3, s4, s5};

        for (Student student : arr) {
            Student.print(student);
        }

        double averageGander = Student.average(arr);
        System.out.println("average of student: " + averageGander);
    }

}

class Student {
    static int count = 0;
    String name;
    int stuId = 2041392401;
    String gender;

    static int age = 20;
    double score;

    public Student() {
    }

    public Student(String name, String gender, double score) {
        this.name = name;
        this.stuId += count;
        this.gender = gender;
        this.score = score;
        count++;
    }

    public static void print(Student student) {
        System.out.println(
                "name: " + student.name + "\t" +
                        "studentId: " + student.stuId + "\t" +
                        "gender: " + student.gender + "\t" +
                        "age: " + Student.age + "\t" +
                        "score: " + student.score);
    }

    public static double average(Student[] student) {
        double sum = 0;
        for (Student s1 : student) {
            sum += s1.score;
        }
        return sum/student.length;
    }
}
