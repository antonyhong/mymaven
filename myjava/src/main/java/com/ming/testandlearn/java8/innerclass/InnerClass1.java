package com.ming.testandlearn.java8.innerclass;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by hongyongming on 2015/11/5.
 */
public class InnerClass1 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>(){{
            add("123456qwe");
        }};
    }
}
