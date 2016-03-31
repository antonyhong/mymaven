package com.ming.testandlearn.inherit;

/**
 * Created by hongyongming on 2016/3/31.
 */
public class Parent {
    public final static int COUNT =1;

    public void sayHello(){
        System.out.println("Parent say hello~");

    }
}

class Child extends  Parent{

    @Override
    public void sayHello() {
        System.out.println("Parent say hello~");
    }
}
