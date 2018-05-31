package com.ryan.baidu.testandlearn.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ryan.baidu.Student;

import java.util.Date;

/**
 * Created by hongyongming on 2017/3/14.
 *
 * json 规范中 field是需要是使用引号(双引号,或者单引号)
 *
 */
public class JsonTest{
    public static void main(String[] args) {
        Student s1 = new Student("hong",12,"football",new Date());
        String jsonStr = JSON.toJSONString(s1, SerializerFeature.WriteDateUseDateFormat);
        System.out.println(jsonStr);

        Student s2 = JSON.parseObject(jsonStr,Student.class);
        System.out.println(JSONObject.toJSONString(s2));
//        s1 = new Student("hong",12,null,null);
//         jsonStr = JSON.toJSONString(s1,SerializerFeature.WriteMapNullValue);
//        System.out.println(jsonStr);
    }
}
