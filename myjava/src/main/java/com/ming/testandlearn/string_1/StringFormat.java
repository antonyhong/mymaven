package com.ming.testandlearn.string_1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hongyongming on 2015/12/23.
 */
public class StringFormat {

    public static void main(String[] args) {
        //diffBetweenStringFormatAndPlus();
        stringTrim();
    }

    private static void stringTrim() {
        List<String> testStrs = new ArrayList<String>() {{
            add("hello \r\t\n");
            add("\thello");
            add("\thello \\r \\n");
        }};

        testStrs.forEach(str -> {
                    System.out.println("**************************");
                    System.out.println(str.trim());
                    //System.out.println(str);
                    System.out.println("**************************");
                }
        );
    }

    private static void diffBetweenStringFormatAndPlus() {
        long start = System.currentTimeMillis();
        String str1 = "欢迎";
        String str2 = "广州";
        for (int i = 0; i < 10; i++) {
            //System.out.println(string_1.format("%s%s%s",str1,i,str2));
        }
        //System.out.println("string_1 format耗时:"+ (System.currentTimeMillis()-start));


        start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            System.out.println(str1 + i + str2);
        }
        System.out.println("string_1 +耗时:" + (System.currentTimeMillis() - start));
    }
}
