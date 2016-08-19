package com.ming.testandlearn.reflect.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by HONGYONGMING on 2016/8/19.
 */
public class ProxyTest {
    public static void main(String[] args) {

        Object stu = Proxy.newProxyInstance(Stu.class.getClassLoader(),new Class[]{Stu.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return method.invoke(proxy,args);
            }
        });


        
    }
}
