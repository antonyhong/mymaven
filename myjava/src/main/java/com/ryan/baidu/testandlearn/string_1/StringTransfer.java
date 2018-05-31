package com.ryan.baidu.testandlearn.string_1;

import java.io.ByteArrayInputStream;
import java.nio.charset.Charset;

/**
 * Created by hongyongming on 2016/2/18.
 */
public class StringTransfer {
    public static void main(String[] args) {
        testByteArrayInputStream();
        //test();
    }

    private static void testByteArrayInputStream(){
        String content  = "I'm a string_1";
        byte[] StrByte =  content.getBytes(Charset.forName("utf-8"));
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(StrByte);


    }

    private static void test(){
        String v  = (String) getIntObj();
        System.out.println(v);
    }

    private static Object getIntObj(){
        return Integer.valueOf(15);
    }
}
