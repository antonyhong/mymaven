package com.ryan.baidu.testandlearn;

public class ClassIntialOrderTest {
    public static void main(String[] args) {
        Singleton1 singleton1 = Singleton1.getInstance();
        System.out.println(singleton1.counter1);
        System.out.println(singleton1.counter2);
        Singleton2 singleton2 = Singleton2.getInstance();
        System.out.println(singleton2.counter1);
        System.out.println(singleton2.counter2);
        double d= 10.00;
        int i =10;
        System.out.println(d==i);
    }

}
class Singleton1 {
    private static Singleton1 singleton = new Singleton1();
    public static int counter1;
    public static int counter2 = 0;

    public Singleton1() {
        counter1++;
        counter2++;
    }

    public static Singleton1 getInstance() {
        return singleton;
    }
}

class Singleton2 {
    public static int counter1;
    public static int counter2 = 0;
    private static Singleton2 singleton = new Singleton2();

    public Singleton2() {
        counter1++;
        counter2++;
    }

    public static Singleton2 getInstance() {
        return singleton;
    }
}
