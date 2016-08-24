package com.ming.testandlearn.reflect.proxy.cglibtest;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by HONGYONGMING on 2016/8/24.
 */
public class AroundAdvice implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        //可以判断处理需要增强的方法
        if(method.getName().equals("eat")){
            return methodProxy.invokeSuper(o, objects);
        }

        System.out.println("【AOP】 before run method...");
        // 这里跟JDK。proxy一样不能用 method.invoke();会导致死循环
        Object result = methodProxy.invokeSuper(o, objects);
        System.out.println("【AOP】after run method...");
        return result;
    }
}
