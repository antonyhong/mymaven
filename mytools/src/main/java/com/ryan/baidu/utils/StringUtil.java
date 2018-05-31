package com.ryan.baidu.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Collection;

/**
 * Created by hongyongming on 2015/9/22.
 */
public class StringUtil {
    public static boolean isBlankStr(Object obj) {
        return obj == null || obj instanceof String && StringUtils.isBlank(obj.toString());
    }
    public static String toString(Object value) {
        return objToString(value, (String)null);
    }

    public static String toString(Object value, String defaultVal) {
        return objToString(value, defaultVal);
    }

    private static String objToString(Object value, String defaultVal) {
        if(value instanceof String) {
            return (String)value;
        } else {
            Object rs = getSimpleObj(value);
            return rs == null?defaultVal:rs.toString();
        }
    }
    private static Object getSimpleObj(Object value) {
        Object rs = null;
        if(value != null) {
            if(value instanceof Object[]) {
                Object[] vals = (Object[])((Object[])value);
                rs = vals.length > 0?vals[0]:rs;
            } else if(value instanceof Collection) {
                Collection vals1 = (Collection)value;
                rs = vals1.isEmpty()?rs:vals1.iterator().next();
            } else {
                rs = value;
            }
        }

        return rs;


    }
}
