package com.ming.testandlearn.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ming.Student;

import java.util.Date;

/**
 * Created by hongyongming on 2017/3/14.
 */
public class JsonTest{
    public static void main(String[] args) {
        Student s1 = new Student("hong",12,"football",new Date());
        String jsonStr = JSON.toJSONString(s1, SerializerFeature.WriteDateUseDateFormat);
        System.out.println(jsonStr);

        s1 = new Student("hong",12,null,null);
         jsonStr = JSON.toJSONString(s1,SerializerFeature.WriteMapNullValue);
        System.out.println(jsonStr);
    }
}
