package com.ryan.baidu.testandlearn.java8.lambda;

/**
 * Created by hongyongming on 2016/5/13.
 */
public class LambdaTest {

    /**
     * @FunctionalInterface
     * 函数式接口,可以方便得转化为 lambda
     * 如: Runnable ,Callable
     *
     *
     * */
    public static void main(String[] args) {
        DoSomeThing(new IFuncInterface() {
            @Override
            public void doSomthing(String str) {
                System.out.println(str);
            }
        });

        DoSomeThing_1(new INotFuncInterface() {
            @Override
            public void doSomthing(String str) {
                System.out.print("hello");
            }

            @Override
            public void saySome() {

            }
        });
    }

    public static void DoSomeThing(IFuncInterface func){
        func.doSomthing("IFuncInterface:something");
    }
    public static void DoSomeThing_1(INotFuncInterface func){
        func.doSomthing("INotFuncInterface:something");
    }


}

