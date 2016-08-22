package com.ming.testandlearn.reflect.aopsample;

/**
 * Created by HONGYONGMING on 2016/8/22.
 */
public class Car implements IVehical {
    @Override
    public void run() {
        System.out.println("car is running");
    }
}
