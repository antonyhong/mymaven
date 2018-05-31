package com.ryan.baidu.utils;
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.PrintWriter;
import java.io.StringWriter;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CommonUtil {
    public CommonUtil() {
    }

    public static boolean isBlankStr(Object obj) {
        return obj == null || obj instanceof String && StringUtils.isBlank(obj.toString());
    }

    public static boolean equal(Object obj1, Object obj2) {
        return obj1 != null && obj2 != null && obj1.getClass() == obj2.getClass()?obj1.equals(obj2):false;
    }

    public static Object get(Object key, Map<?, ?> param) {
        return param.get(key);
    }

    public static boolean isInListWithString(String obj, String[] listObj) {
        if(listObj != null && listObj.length > 0) {
            for(int i = 0; i < listObj.length; ++i) {
                if(listObj[i].equals(obj)) {
                    return true;
                }
            }

            return false;
        } else {
            return false;
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

    public static String getString(Object key, Map<?, ?> param) {
        return toString(get(key, param));
    }

    public static String getString(Object key, Map<?, ?> param, String defaultVal) {
        return toString(get(key, param), defaultVal);
    }

    public static <T> List<T> getList(Object key, Map<?, ?> param) {
        Object val = get(key, param);
        return val instanceof List?(List)val:null;
    }

    public static Integer getInt(Object key, Map<?, ?> param, Integer defaultVal) {
        return toInt(get(key, param), defaultVal);
    }

    public static Integer toInt(Object obj, Integer defaultVal) {
        if(obj == null) {
            return defaultVal;
        } else if(obj instanceof Integer) {
            return (Integer)obj;
        } else if(obj instanceof Boolean) {
            return BooleanUtils.toIntegerObject((Boolean) obj);
        } else {
            String value = toString(obj);
            return isInt(value)?Integer.valueOf((int) NumberUtils.toDouble(value)):(value.equals("true")?Integer.valueOf(1):(value.equals("false")?Integer.valueOf(0):defaultVal));
        }
    }

    public static Long getLong(Object key, Map<?, ?> param) {
        return getLong(key, param, (Long)null);
    }

    public static Long getLong(Object key, Map<?, ?> param, Long defaultVal) {
        return toLong(get(key, param), defaultVal);
    }

    public static Long toLong(Object obj) {
        return toLong(obj, (Long)null);
    }

    public static Long toLong(Object obj, Long defaultVal) {
        if(obj == null) {
            return defaultVal;
        } else if(obj instanceof Long) {
            return (Long)obj;
        } else if(obj instanceof Integer) {
            return Long.valueOf((long)((Integer)obj).intValue() * 1L);
        } else if(obj instanceof Boolean) {
            return Long.valueOf((long)BooleanUtils.toIntegerObject((Boolean)obj).intValue() * 1L);
        } else {
            String value = toString(obj);
            return isLong(value)?Long.valueOf((long)NumberUtils.toDouble(value)):(value.equals("true")?Long.valueOf(1L):(value.equals("false")?Long.valueOf(0L):defaultVal));
        }
    }

    public static Float getFloat(String key, Map<?, ?> param) {
        return getFloat(key, param, (Float)null);
    }

    public static Float getFloat(String key, Map<?, ?> param, Float defaultVal) {
        return toFloat(get(key, param), defaultVal);
    }

    public static Float toFloat(Object obj, Float defaultVal) {
        String value = toString(obj);
        return value == null?defaultVal:(isNumer(value)?Float.valueOf(value):defaultVal);
    }

    public static Double getDouble(String key, Map<?, ?> param) {
        return getDouble(key, param, (Double)null);
    }

    public static Double getDouble(String key, Map<?, ?> param, Double dafaultVal) {
        return toDouble(get(key, param), dafaultVal);
    }

    public static Double toDouble(Object obj, Double dafaultVal) {
        String value = toString(obj);
        return value == null?dafaultVal:(isNumer(value)?Double.valueOf(value):dafaultVal);
    }

    public static Boolean getBoolean(Object key, Map<?, ?> param) {
        return getBoolean(key, param, (Boolean)null);
    }

    public static Boolean getBoolean(Object key, Map<?, ?> param, Boolean defaultVal) {
        return toBoolean(get(key, param), defaultVal);
    }

    public static Boolean toBoolean(Object obj, Boolean defaultVal) {
        String value = toString(obj);
        return StringUtils.isBlank(value)?defaultVal:(isInt(value)?Boolean.valueOf(Integer.valueOf(value).intValue() == 1):Boolean.valueOf(value));
    }

    public static boolean isInt(String str) {
        if(!isNumer(str)) {
            return false;
        } else {
            Double d = Double.valueOf(str);
            return d.doubleValue() <= 2.147483647E9D && d.doubleValue() >= -2.147483648E9D;
        }
    }

    public static boolean isLong(String str) {
        if(!isNumer(str)) {
            return false;
        } else {
            Double d = Double.valueOf(str);
            return d.doubleValue() <= 9.223372036854776E18D && d.doubleValue() >= -9.223372036854776E18D;
        }
    }

    public static boolean isNumer(String str) {
        return NumberUtils.isNumber(str);
    }

    public static Date getDate(String key, Map<?, ?> map) {
        Object object = map.get(key);
        Date value;
        if(object == null) {
            value = null;
        } else if(object instanceof Date) {
            value = (Date)object;
        } else if(object instanceof Long) {
            value = new Date(((Long)object).longValue());
        } else {
            try {
                value = DateUtils.parseAllFormat(getString(key, map));
            } catch (Exception var5) {
                return null;
            }
        }

        return value;
    }

    public static boolean collectionEmpty(Collection<?> list) {
        return list == null || list.isEmpty();
    }

    public static boolean collectionNotEmpty(Collection<?> list) {
        return !collectionEmpty(list);
    }

    public static boolean mapEmpty(Map<? extends Object, ? extends Object> map) {
        return map == null || map.isEmpty();
    }

    public static boolean arrEmpty(Object[] arr) {
        return arr == null || arr.length == 0;
    }

    public static String toStringWithSplit(Collection<?> list, String split) {
        StringBuilder sb = new StringBuilder();
        int splitLen = split.length();
        if(list != null) {
            Iterator var4 = list.iterator();

            while(true) {
                Object obj;
                do {
                    do {
                        if(!var4.hasNext()) {
                            return sb.length() == 0?sb.toString():sb.substring(0, sb.length() - splitLen);
                        }

                        obj = var4.next();
                    } while(obj == null);
                } while(obj instanceof String && obj.equals(""));

                sb.append(obj).append(split);
            }
        } else {
            return sb.length() == 0?sb.toString():sb.substring(0, sb.length() - splitLen);
        }
    }

    public static List<? extends Object> toList(Object obj) {
        if(obj == null) {
            return Collections.emptyList();
        } else if(obj instanceof Object[]) {
            return Arrays.asList((Object[])((Object[])obj));
        } else {
            ArrayList objList = new ArrayList();
            if(obj instanceof Collection) {
                objList.addAll((Collection)obj);
            } else {
                int var3;
                int var4;
                if(obj instanceof byte[]) {
                    byte[] var2 = (byte[])((byte[])obj);
                    var3 = var2.length;

                    for(var4 = 0; var4 < var3; ++var4) {
                        byte i = var2[var4];
                        objList.add(Byte.valueOf(i));
                    }
                } else if(obj instanceof int[]) {
                    int[] var7 = (int[])((int[])obj);
                    var3 = var7.length;

                    for(var4 = 0; var4 < var3; ++var4) {
                        int var11 = var7[var4];
                        objList.add(Integer.valueOf(var11));
                    }
                } else if(obj instanceof long[]) {
                    long[] var8 = (long[])((long[])obj);
                    var3 = var8.length;

                    for(var4 = 0; var4 < var3; ++var4) {
                        long var12 = var8[var4];
                        objList.add(Long.valueOf(var12));
                    }
                } else if(obj instanceof float[]) {
                    float[] var9 = (float[])((float[])obj);
                    var3 = var9.length;

                    for(var4 = 0; var4 < var3; ++var4) {
                        float var13 = var9[var4];
                        objList.add(Float.valueOf(var13));
                    }
                } else if(obj instanceof double[]) {
                    double[] var10 = (double[])((double[])obj);
                    var3 = var10.length;

                    for(var4 = 0; var4 < var3; ++var4) {
                        double var14 = var10[var4];
                        objList.add(Double.valueOf(var14));
                    }
                } else {
                    objList.add(obj);
                }
            }

            return objList;
        }
    }

    public static int randIntBetweent(int min, int max) {
        return randInt(max - min + 1) + min - 1;
    }

    public static int randInt(int n) {
        return (int)(Math.random() * (double)n) + 1;
    }

    public static int randInt(int n, int exclude) {
        int i;
        do {
            i = randInt(n);
        } while(i == exclude);

        return i;
    }

    public static Map<String, Object> toMap(String key, Object value) {
        HashMap m = new HashMap();
        m.put(key, value);
        return m;
    }

    public static <T> List<T> getCachePager(int offset, int limit, List<T> all) {
        if(collectionEmpty(all)) {
            return Collections.emptyList();
        } else if(2147483647 != limit && limit > 0) {
            int totalCount = all.size();
            if(offset >= totalCount) {
                return Collections.emptyList();
            } else {
                int toIndex = offset + limit;
                toIndex = toIndex >= totalCount?totalCount:toIndex;
                return all.subList(offset, toIndex);
            }
        } else {
            return all;
        }
    }

    public static String[] split(String str, String splitStrNotRegex) {
        return StringUtils.splitByWholeSeparator(str, splitStrNotRegex);
    }

    public static void mapValue2SimpleObj(Map<?, Object> m) {
        Iterator var1 = m.entrySet().iterator();

        while(var1.hasNext()) {
            Map.Entry e = (Map.Entry)var1.next();
            e.setValue(getSimpleObj(e.getValue()));
        }

    }

    public static int getPageNum(int total, int limit) {
        return limit <= 0?1:total / limit + (total % limit > 0?1:0);
    }

    public static int getOffset(int curPage, int limit) {
        return (curPage - 1) * limit;
    }

    public static int getCurPageNum(int offset, int limit) {
        return limit == 0?0:offset / limit + 1;
    }

    public static Set<String> genSetWithSplit(String str) {
        return genStringSetWithSplit(str, ",");
    }

    public static Set<String> genStringSetWithSplit(String str, String split) {
        HashSet ts = new HashSet();
        if(StringUtils.isBlank(str)) {
            return ts;
        } else {
            String[] var3 = str.split(split);
            int var4 = var3.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                String strId = var3[var5];
                ts.add(strId);
            }

            return ts;
        }
    }

    public static List<Long> genLongListWithSplit(String str, String split) {
        ArrayList ts = new ArrayList();
        if(StringUtils.isBlank(str)) {
            return ts;
        } else {
            String[] var3 = str.split(split);
            int var4 = var3.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                String strVal = var3[var5];
                Long strLongVal = toLong(strVal);
                if(strLongVal != null) {
                    ts.add(strLongVal);
                }
            }

            return ts;
        }
    }

    public static List<Integer> genIntListWithSplit(String str, String split) {
        ArrayList ts = new ArrayList();
        if(StringUtils.isBlank(str)) {
            return ts;
        } else {
            String[] var3 = str.split(split);
            int var4 = var3.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                String strVal = var3[var5];
                Integer strIntVal = toInt(strVal, (Integer)null);
                if(strIntVal != null) {
                    ts.add(strIntVal);
                }
            }

            return ts;
        }
    }

    public static String collection2String(Collection<?> collection, String splitor) {

        if(!CommonUtil.collectionEmpty(collection) && !StringUtils.isBlank(splitor)) {
            StringBuilder sb = new StringBuilder();
            collection.forEach((obj) -> {
                sb.append(obj).append(splitor);
            });
            return sb.substring(0, sb.length() - splitor.length());
        } else {
            return "";
        }
    }

    public static String getStackMessage(Exception e) {
        StringWriter stringWriter = new StringWriter();
        e.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }
}
