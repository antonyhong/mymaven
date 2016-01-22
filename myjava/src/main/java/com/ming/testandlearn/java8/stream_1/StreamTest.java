package com.ming.testandlearn.java8.stream_1;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

/**
 * Created by hongyongming on 2015/12/28.
 */
public class StreamTest {

    public static void main(String[] args) {
        Grade grade1 = new Grade("grade 1", Arrays.asList(
           new Class("class1",Arrays.asList(new Student_inner("Tom",11),new Student_inner("Any",10),new Student_inner("Bob",16))),
                new Class("class2",Arrays.asList(new Student_inner("Cat",11),new Student_inner("Dog",13),new Student_inner("Pig",11))),
                new Class("class3",Arrays.asList(new Student_inner("Strawberry",14),new Student_inner("Apple",10),new Student_inner("Banana",9)))
        ));

         List<String> stuNameThatAgeLargerThan11 =  grade1.classes.stream().flatMap(c->c.students.stream())
                .filter(stu->stu.age >11)
                .map(stu->stu.name)
                .collect(toList());

        List<String> stuNameThatAgeLargerThan12 =  grade1.classes.stream().flatMap(c->c.students.stream())
                .filter(stu -> stu.age > 11)
                .map(stu -> stu.name)
                .collect(Collectors.toCollection(ArrayList::new));/****指定具体的类型*/

       Map<Boolean,List<Student_inner>> map =
               grade1.classes.stream().flatMap(c -> c.students.stream())
                .filter(stu -> stu.age > 11)
                .collect(partitioningBy(stu -> stu.age > 10));/****分块*/

        Map<Integer,List<Student_inner>> map2 =
                grade1.classes.stream().flatMap(c -> c.students.stream())
                        .filter(stu -> stu.age > 11)
                        .collect(groupingBy(stu->stu.age));/****分组*/

        Map<Integer,List<Student_inner>> map3 =
                grade1.classes.parallelStream().flatMap(c -> c.students.stream())
                        .filter(stu -> stu.age > 11)
                        .collect(groupingBy(stu->stu.age));/****并行*/





        Map<String,Student_inner> mapName2Stu = grade1.classes.stream().flatMap(c -> c.students.stream())
                .filter(stu->stu.age >11)
                .collect(Collectors.toMap(stu->stu.name,p->p));


//        grade1.classes.stream().min();
//        grade1.classes.stream().max();
//        grade1.classes.stream().reduce();

        /** 都是使用reduce()*/
        System.out.println(stuNameThatAgeLargerThan11);
        System.out.println(mapName2Stu);
    }

    public static  class Grade{
        public String gradeNo;
        public List<Class> classes;

        public Grade(String gradeNo, List<Class> classes) {
            this.gradeNo = gradeNo;
            this.classes = classes;
        }
    }
    public static class Class{
        public String classNo;
        public List<Student_inner> students;

        public Class(String classNo, List<Student_inner> students) {
            this.classNo = classNo;
            this.students = students;
        }
    }
    public static class Student_inner{
        public String name;
        public int age;

        public Student_inner(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }
}
