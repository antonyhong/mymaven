package com.ryan.baidu.testandlearn.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.ryan.baidu.Student;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JsonPathTest {
    public static void main(String[] args) {
        testMap();
    }

    private static void testMap(){
        JSONObject jsonObject = new JSONObject();

        JSONPath.set(jsonObject,"$.session.user","hongyonming");
        JSONPath.set(jsonObject,"$.session.pw","123456");


        Student student = new Student();
        student.setAge(12);
        student.setBirthday(new Date());
        student.setHobbby("hahaha");
        student.setName("lisi");
        JSONPath.set(jsonObject,"$.session.student",student);

        Map<String,String> map = new HashMap<>();
        map.put("user1","Ryanhong");
        map.put("pw1","545646");

        JSONPath.set(jsonObject,"sessionMap",map);
        System.out.println("【Add map sessionMap 】 updateMap:\r\n"+jsonObject.toJSONString());

        JSONPath.set(jsonObject,"sessionMap.class","newclass");
        JSONPath.set(jsonObject,"sessionMap.class","NewRyanhong");
        JSONPath.remove(jsonObject,"sessionMap.pw1");
        System.out.println("【After updateMap】:\r\n"+jsonObject.toJSONString());
        System.out.println("【After updateMap,map】:\r\n"+JSON.toJSONString(map));


        Map<String,String> map2 = new HashMap<>();
        map2.put("user1","Ryanhong");
        map2.put("pw1","545646");
        JSONPath.set(jsonObject,"$.session.object",map2);
        String jsonStr = jsonObject.toJSONString();
        System.out.println("【Add Map2<> session.object】:\r\n"+jsonStr);

//        JSONObject jo = JSON.parseObject(jsonStr);
//        Object object = JSONPath.eval(jo,"$.session.student");
//        System.out.println(object);
//
//        Object jObj = jo.getJSONObject("session").get("student");
//        System.out.println(jObj);

    }

}
