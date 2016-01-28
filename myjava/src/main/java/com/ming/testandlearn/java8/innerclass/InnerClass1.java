package com.ming.testandlearn.java8.innerclass;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by hongyongming on 2015/11/5.
 */
public class InnerClass1 {
    public static void main(String[] args) {
        /**这两种写个应该在序列化的时候有有差异,因为在RPC调用的时候报报过*/
        List<String> list = new ArrayList<String>(){{
            add("123456qwe");
        }};

        List<String> list2 = new ArrayList<String>();
        list.add("12313");
    }
}
