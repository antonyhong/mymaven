package com.ming.excel.excelopr;

import java.lang.reflect.Type;

public class ExcelHeader implements Comparable<ExcelHeader> {
    private String methodName;  //字段的方法名称(字符串形式,保存字段的getXXX方法)
    private String title;      //字段在Excel中对应的标题
    private int order;         //记录每个字段的先后顺序(越大越靠前)
    private Type type;         //字段的类型

    public ExcelHeader(String methodName, String title, int order, Type type) {
        this.methodName = methodName;
        this.title = title;
        this.order = order;
        this.type = type;
    }

    public ExcelHeader(String methodName, String title, Type type) {
        this.methodName = methodName;
        this.title = title;
        this.type = type;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    /**
     * 根据order进行排序
     */
    @Override
    public int compareTo(ExcelHeader o) {
        if (o.getOrder() == this.getOrder()) {
            return 0;
        }
        return this.getOrder() > o.getOrder() ? 1 : -1;
    }
}
