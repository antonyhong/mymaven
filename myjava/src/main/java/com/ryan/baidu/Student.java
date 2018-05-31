package com.ryan.baidu;

import java.util.Date;
import java.util.Timer;

/**
 * Created by hongyongming on 2015/10/20.
 */
public class Student {
    private String name;
    private int age;
    private String hobbby;
    private Date birthday;

    public Student() {
    }

    public Student(String name, int age, String hobbby) {
        this.name = name;
        this.age = age;
        this.hobbby = hobbby;
    }

    public Student(String name, int age, String hobbby, Date birthday) {
        this.name = name;
        this.age = age;
        this.hobbby = hobbby;
        this.birthday = birthday;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
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

    public static void main(String[] args) {



    }

    public void consume(String userId){

    }


}
