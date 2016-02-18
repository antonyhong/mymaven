package com.ming.String;

import java.io.ByteArrayInputStream;
import java.nio.charset.Charset;
import java.util.Base64;

/**
 * Created by hongyongming on 2016/2/18.
 */
public class StringTransfer {
    public static void main(String[] args) {
        String content  = "I'm a String";
        byte[] StrByte =  content.getBytes(Charset.forName("utf-8"));
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(StrByte);


    }
}
