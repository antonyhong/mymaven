package com.ryan.baidu.utils;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by hongyongming on 2016/9/22.
 */
public class JsonUtilsTest {

    @Test
    public void testSerialize() throws Exception {
        List<String> list = Arrays.asList("123", "12312", "56436");
        System.out.println(JsonUtils.serialize(list));
    }
}