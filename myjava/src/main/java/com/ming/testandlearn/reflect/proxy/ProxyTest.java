package com.ming.testandlearn.reflect.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by HONGYONGMING on 2016/8/19.
 */
public class ProxyTest {
    public static void main(String[] args) {

        Object stu = Proxy.newProxyInstance(Stu.class.getClassLoader(),new Class[]{IStu.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                if(method.getName().equals("sayHello")){
                    System.out.println("hello from proxy");
                    return  null;
                }
                // 无法正常运行
                 method.invoke(proxy,args);
                return null;
            }
        });

        ((IStu)stu).sayHello();
        ((IStu)stu).doSomeThing("reading");



    }
}
