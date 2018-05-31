
package com.ryan.baidu.utils;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.lang3.StringUtils;

import java.io.InputStream;

public class JsonUtils {
   // private static final Logger Log = LoggerFactory.getLogger(JsonUtils.class);

    public JsonUtils() {
    }

    public static <T> T deserialize(String jsonStr, Class<T> cla) {
        try {
            return StringUtils.isBlank(jsonStr)?cla.newInstance():LazyInit.objMapper.readValue(jsonStr, cla);
        } catch (Exception ex) {
            System.err.println(ex);
            return null;
        }
    }

//    public static <T> T deserialize(String jsonStr, TypeReference<T> valueTypeRef) {
//        try {
//            return StringUtils.isBlank(jsonStr)?null:( T )(JsonUtil.LazyInit.objMapper.readValue(jsonStr, valueTypeRef));
//        } catch (Exception var3) {
//            Log.error("", var3);
//            return null;
//        }
//    }

    public static <T> T deserializeUnchecked(String jsonStr, Class<T> cla) {
        boolean savedFailOnUnknownProperties =LazyInit.objMapper.isEnabled(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
       LazyInit.objMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Object result = deserialize(jsonStr, cla);
       LazyInit.objMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, savedFailOnUnknownProperties);
        return (T)result;
    }

//    public static <T> T deserializeUnchecked(String jsonStr, TypeReference<T> valueTypeRef) {
//        boolean savedFailOnUnknownProperties =LazyInit.objMapper.isEnabled(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
//       LazyInit.objMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        Object result = deserialize(jsonStr, valueTypeRef);
//       LazyInit.objMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, savedFailOnUnknownProperties);
//        return (T)result;
//    }

    public static <T> T deserializeFile(String file, Class<T> cla) {
        return deserializeByInputStream(JsonUtils.class.getResourceAsStream(file), cla);
    }

    public static <T> T deserializeByInputStream(InputStream io, Class<T> cla) {
        try {
            return LazyInit.objMapper.readValue(io, cla);
        } catch (Exception ex) {
            System.err.println(ex);
            return null;
        }
    }

    public static String serialize(Object obj) {
        try {
            return LazyInit.objMapper.writeValueAsString(obj);
        } catch (Exception ex) {
            System.err.println(ex);
            return "{}";
        }
    }

    private static final class LazyInit {
        private static final ObjectMapper objMapper = new ObjectMapper();

        private LazyInit() {
        }

        static {
            objMapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
            objMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
            objMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
            objMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        }
    }
}


