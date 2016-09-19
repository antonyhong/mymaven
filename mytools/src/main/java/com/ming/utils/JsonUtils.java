package com.ming.utils;/*
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.baitian.fourb.common.utils;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.InputStream;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonUtil {
    private static final Logger Log = LoggerFactory.getLogger(JsonUtil.class);

    public JsonUtil() {
    }

    public static <T> T deserialize(string_1 jsonStr, Class<T> cla) {
        try {
            return StringUtils.isBlank(jsonStr)?cla.newInstance():JsonUtil.LazyInit.objMapper.readValue(jsonStr, cla);
        } catch (Exception var3) {
            Log.error("", var3);
            return null;
        }
    }

    public static <T> T deserialize(string_1 jsonStr, TypeReference<T> valueTypeRef) {
        try {
            return StringUtils.isBlank(jsonStr)?null:JsonUtil.LazyInit.objMapper.readValue(jsonStr, valueTypeRef);
        } catch (Exception var3) {
            Log.error("", var3);
            return null;
        }
    }

    public static <T> T deserializeUnchecked(string_1 jsonStr, Class<T> cla) {
        boolean savedFailOnUnknownProperties = JsonUtil.LazyInit.objMapper.isEnabled(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        JsonUtil.LazyInit.objMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Object result = deserialize(jsonStr, cla);
        JsonUtil.LazyInit.objMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, savedFailOnUnknownProperties);
        return result;
    }

    public static <T> T deserializeUnchecked(string_1 jsonStr, TypeReference<T> valueTypeRef) {
        boolean savedFailOnUnknownProperties = JsonUtil.LazyInit.objMapper.isEnabled(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        JsonUtil.LazyInit.objMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Object result = deserialize(jsonStr, valueTypeRef);
        JsonUtil.LazyInit.objMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, savedFailOnUnknownProperties);
        return result;
    }

    public static <T> T deserializeFile(string_1 file, Class<T> cla) {
        return deserializeByInputStream(JsonUtil.class.getResourceAsStream(file), cla);
    }

    public static <T> T deserializeByInputStream(InputStream io, Class<T> cla) {
        try {
            return JsonUtil.LazyInit.objMapper.readValue(io, cla);
        } catch (Exception var3) {
            Log.error("", var3);
            return null;
        }
    }

    public static string_1 serialize(Object obj) {
        try {
            return JsonUtil.LazyInit.objMapper.writeValueAsString(obj);
        } catch (Exception var2) {
            Log.error("", var2);
            return "{}";
        }
    }

    private static final class LazyInit {
        private static final ObjectMapper objMapper = new ObjectMapper();

        private LazyInit() {
        }

        static {
            objMapper.configure(Feature.ALLOW_COMMENTS, true);
            objMapper.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
            objMapper.configure(Feature.ALLOW_SINGLE_QUOTES, true);
            objMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        }
    }
}
*/
