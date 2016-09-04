package com.ming.testandlearn.reflect.proxy.jdkproxy;

/**
 * Created by HONGYONGMING on 2016/8/19.
 */
public class Stu implements IStu{
    @Override
    public void sayHello(){
        System.out.println("how do you do ~!");
    }

    @Override
    public void doSomeThing(String some) {
        System.out.println("I'm doing "+ some);
    }


}
