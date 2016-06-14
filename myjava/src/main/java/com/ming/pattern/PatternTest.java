package com.ming.pattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by hongyongming on 2016/6/13.
 */
public class PatternTest {

    public static void main(String[] args) {

        Pattern Pattern_GUID = Pattern.compile(".*\\[(.{36})\\].*");
        String reInfo = "新增申报成功[4CDE1CFD-EDED-46B1-946C-B8022E42FC9Q]";
        Matcher matcher = Pattern_GUID.matcher(reInfo);
        if(matcher.find()){
            System.out.println(matcher.group(1));
        }else {
            System.out.println("NOT FOUND~");
        }
    }
}
