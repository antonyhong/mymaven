package com.ming.testandlearn.reflect.proxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by HONGYONGMING on 2016/8/19.
 * JDK 动态代理
 *
 * ref:
 *     [1]http://www.cnblogs.com/techyc/p/3455950.html
 *     [2]https://www.ibm.com/developerworks/cn/java/j-lo-spring-principle/
 *
 *方法 Proxy.newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h)
 *        参数:
 *        classLoader: 类加载器,将class 加载到jvm的工具。一般与被加载的类取同一个Loader。
 *        InvocationHandler：所有动态代理类的方法调用，都会交由InvocationHandler接口实现类里的invoke()方法去处理。这是动态代理的关键所在。
 *        该对象的invoke（）就是最终的行为。
 *
 * 方法 InvocationHandler.invoke(Object proxy, Method method, Object[] args)
 *          不能使用method.invoke(proxy,args),这样会再次触发invoke方法,导致死循环.
 */
public class ProxyTest {
    public static void main(String[] args) {

        ClassLoader classLoader = Stu.class.getClassLoader();
        System.out.println(Stu.class.getClassLoader().getParent());
        System.out.println(Stu.class.getClassLoader().getParent().getParent());

        System.out.println(System.getProperty("classPath"));


        Object stu = Proxy.newProxyInstance(classLoader,new Class[]{IStu.class}, new InvocationHandler() {
        Stu realStu = new Stu();
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if(method.getName().equals("sayHello")){
                    System.out.println("hello from proxy");
                    return  null;
                }else{
                    // method.invoke(proxy,args)
                    System.out.println("doSomething before invoke");
                    method.invoke(realStu,args);
                    System.out.println("doSomething after invoke");
                    return null;
                }
            }
        });
        ((IStu)stu).sayHello();
        ((IStu)stu).doSomeThing("reading");
    }
}
