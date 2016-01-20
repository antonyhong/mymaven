package com.ming;

/**
 * Created by hongyongming on 2015/10/20.
 */
public class Student {
    private String name;
    private int age;
    private String hobbby;

    public Student(String name, int age, String hobbby) {
        this.name = name;
        this.age = age;
        this.hobbby = hobbby;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getHobbby() {
        return hobbby;
    }

    public void setHobbby(String hobbby) {
        this.hobbby = hobbby;
    }
}
