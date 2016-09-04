package com.ming.testandlearn.reflect.aopsample;

/**
 * Created by HONGYONGMING on 2016/8/22.
 */
public class AOPSample {

    /**
     * 这个sample只是AOP的实现原理原理。
     *
     * */
    public static void main(String[] args) {

        IVehical car = new Car();
        VehicalProxy proxy = new VehicalProxy(car);

        IVehical proxyObj = proxy.create();
        proxyObj.run();
    }
}
