package com.ryan.baidu.testandlearn.clazz;

/**
 * Created by hongyongming on 2017/7/21.
 */
public class TestMain {
    public static void main(String[] args) {
        System.out.println(Square.class.isAssignableFrom(Sharp.class));
        System.out.println(Sharp.class.isAssignableFrom(Square.class));
        System.out.println(Sharp.class.isAssignableFrom(Sharp.class));
    }
}
