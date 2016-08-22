package com.ming.testandlearn.reflect.proxy;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by HONGYONGMING on 2016/8/19.
 * JDK 动态代理
 *
 * ref:http://www.cnblogs.com/techyc/p/3455950.html
 */
public class ProxyTest {
    public static void main(String[] args) {

        /**
         * 参数:InvocationHandler;所有动态代理类的方法调用，都会交由InvocationHandler接口实现类里的invoke()方法去处理。这是动态代理的关键所在
         * */
        Object stu = Proxy.newProxyInstance(Stu.class.getClassLoader(),new Class[]{IStu.class}, new InvocationHandler() {
        Stu realStu = new Stu();
            /**
             *参数说明:
             *  1. 动态代理类的引用，通常情况下不需要它。但可以使用getClass()方法，得到proxy的Class类从而取得实例的类信息，如方法列表，annotation等。
             * 2. 方法对象的引用，代表被动态代理类调用的方法。从中可得到方法名，参数类型，返回类型等等
             * 3. args对象数组，代表被调用方法的参数。注意基本类型(int,long)会被装箱成对象类型(Interger, Long)
             * */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if(method.getName().equals("sayHello")){
                    System.out.println("hello from proxy");
                    return  null;
                }else{
                    // 无法正常运行
                    //method.invoke(proxy,args);
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
