package com.ming.testandlearn.java8.Stream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by hongyongming on 2015/12/28.
 */
public class StreamTest {

    public static void main(String[] args) {
        Grade grade1 = new Grade("grade 1", Arrays.asList(
           new Class("class1",Arrays.asList(new Student("Tom",11),new Student("Any",10),new Student("Bob",16))),
                new Class("class2",Arrays.asList(new Student("Cat",11),new Student("Dog",13),new Student("Pig",11))),
                new Class("class3",Arrays.asList(new Student("Strawberry",14),new Student("Apple",10),new Student("Banana",9)))
        ));

         List<String> stuNameThatAgeLargerThan11 =  grade1.classes.stream().flatMap(c->c.students.stream())
                .filter(stu->stu.age >11)
                .map(stu->stu.name)
                .collect(Collectors.toList());

        Map<String,Student> mapName2Stu = grade1.classes.stream().flatMap(c -> c.students.stream())
                .filter(stu->stu.age >11)
                .collect(Collectors.toMap(stu->stu.name,p->p));

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
        public List<Student> students;

        public Class(String classNo, List<Student> students) {
            this.classNo = classNo;
            this.students = students;
        }
    }
    public static class Student{
        public String name;
        public int age;

        public Student(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }
}
