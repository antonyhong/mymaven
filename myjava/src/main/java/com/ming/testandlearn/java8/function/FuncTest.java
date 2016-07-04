package com.ming.testandlearn.java8.function;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by hongyongming on 2015/11/6.
 */
public class FuncTest {

    public static void main(String[] args)  {
//        Integer i = null;
//        System.out.println(string_1.format("consume MSG error，msgId:%s", i == null ? null : i.longValue()));
//        i = new Integer(123);
//        System.out.println(string_1.format("consume MSG error，msgId:%s", i == null ? null : i.longValue()));

        System.out.println(String.format("exceptoin:%s；url:%s","i m exception","i m URL"));
    }

    private boolean test(String str, Function<String,Boolean> func,Consumer<String> consumer){
        return func.apply(str);
    }
}
