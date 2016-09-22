package com.ming.utils;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

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