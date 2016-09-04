package com.ming.testandlearn.reflect.proxy.cglibtest;

/**
 * Created by HONGYONGMING on 2016/8/24.
 */
public class CglibTest {
    public static void main(String[] args) {
        Chinese chin = ChineseProxyFactory.getAuthInstance();
        System.out.println(chin.sayHello("孙悟空"));

        chin.eat("西瓜");

        System.out.println(chin.getClass());
    }
}
