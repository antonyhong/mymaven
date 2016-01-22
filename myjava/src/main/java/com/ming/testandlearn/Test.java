package com.ming.testandlearn;

import aQute.bnd.build.model.conversions.Converter;

import java.util.Optional;

/**
 * Created by hongyongming on 2015/12/28.
 */
public class Test {
    public static void main(String[] args) {
        Optional<Integer> opt= Optional.empty();

       // Map<String,String> map = new HashMap<>();
        //map.computeIfAbsent()



        int num = 1;
        Converter<Integer, String> stringConverter =
                (from) -> Integer.valueOf(from + num);
        /**num 使用lambda  引用的变量应该是(不用final标识也可) 是final的*/
       //num = 3;
    }
}
