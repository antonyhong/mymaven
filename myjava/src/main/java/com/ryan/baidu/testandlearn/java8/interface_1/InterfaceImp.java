package com.ryan.baidu.testandlearn.java8.interface_1;

/**
 * Created by hongyongming on 2016/1/22.
 */
public class InterfaceImp implements  IterfaceWithDefault{
    @Override
    public String doSomething() {
        return  "I have done something!";
    }

    public static void main(String[] args) {
        InterfaceImp imp = new InterfaceImp();
        System.out.println(imp.doSomething());
        System.out.print(imp.saySomeThing());
    }

}
