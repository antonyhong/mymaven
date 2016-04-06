package com.ming.excel.excelopr;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Consumer;

/**
 * Created by hongyongming on 2016/4/1.
 * 默认excel 导出操作类: 使用注解
 */
public class ExportSheet extends SheetReaderAndWriter implements IExportSheet {
    public static int DEFAULT_START_ROW_INDEX = 1;
    public static boolean DEFAULT_INITHEADER = true;
    public static String DEFAULT_SHEET_NAME = "sheet";
    private int startRowIndex;
    private String sheetName;
    boolean initHeader;
    private List<ExcelHeader> excelHeaders;
    private Map<String, Method> methodMap;
    private List<?> entries;

    public ExportSheet(List<?> entries) {
        this(DEFAULT_SHEET_NAME, entries);
    }

    /** 默认:不采用模板,第0行为列头,第1行开始为数据 */
    public ExportSheet(String sheetName, List<?> entries) {
        this(sheetName, entries, DEFAULT_INITHEADER, DEFAULT_START_ROW_INDEX);
    }

    public ExportSheet(String sheetName, List<?> entries, boolean initHeader) {
        this(sheetName, entries, initHeader, DEFAULT_START_ROW_INDEX);
    }

    public ExportSheet(String sheetName, List<?> entries, boolean initHeader, int startRowIndex) {
        if (entries == null || entries.isEmpty()) {
            throw new RuntimeException("导出数据不能为空");
        }
        this.startRowIndex = startRowIndex;
        this.entries = entries;
        this.sheetName = sheetName;
        this.initHeader = initHeader;

        Class<?> clazz = entries.get(0).getClass();
        excelHeaders = new ArrayList<>();
        methodMap = new HashMap<>();
        initialHeadersAndMethodMap(clazz);
    }

    @Override
    public String getSheetName() {
        return sheetName;
    }

    @Override
    public Consumer<Sheet> getFillSheetFunc() {
        return sheet -> {
            int startRowIndex1 = this.startRowIndex;
            try {
                List<ExcelHeader> headers = getExcelHeaders();
                if (initHeader) {
                    /**输出标题*/
                    setHeaders(sheet, headers, sheet.createRow(0));
                }
                /**输出数据*/
                for (int i = 0; i < getEntries().size(); i++) {
                    Row row = sheet.createRow(i + startRowIndex1);
                    Object obj = getEntries().get(i);

                    for (int j = 0; j < headers.size(); j++) {
                        ExcelHeader header = headers.get(j);
                        Method method = getMethodMap().get(header.getMethodName());
                        if (method == null) {
                            throw new RuntimeException("找不到方法:" + header.getMethodName());
                        }
                        Cell cell = row.createCell(j);
                        Object val = method.invoke(obj);
                        String type = header.getType().toString(); //字符串的形式输出实体每个字段的类型.
                        setCellVal(sheet, row, method, cell, val, type);
                    }
                }
            } catch (Exception e) {
                //logger.error("", e);
                throw new RuntimeException(e);
            }

        };
    }

    private void setHeaders(Sheet sheet, List<ExcelHeader> headers, Row headRow) {
        for (int i = 0; i < headers.size(); i++) {
            ExcelHeader excelHeader = headers.get(i);
            Cell cell = headRow.createCell(i);
            cell.setCellValue(excelHeader.getTitle());
            /**设置时间宽度*/
            if (Date.class.getTypeName().equals(excelHeader.getType().getTypeName())) {
                sheet.setColumnWidth(i, 4500);
            }
        }
    }


    private List<ExcelHeader> getExcelHeaders() {
        return excelHeaders;
    }

    private Map<String, Method> getMethodMap() {
        return methodMap;
    }

    private List<?> getEntries() {
        return entries;
    }

    private void initialHeadersAndMethodMap(Class<?> clazz) {
        List<Class<?>> clazzs = getAllParentClazz(clazz);
        for (Class<?> c : clazzs) {
            Method[] methods = c.getDeclaredMethods();
            String name;
            for (Method method : methods) {
                name = method.getName();
                if (method.isAnnotationPresent(ExcelExportSign.class)) {
                    if (!isLegalGetOrIsMethod(method)) {
                        throw new RuntimeException(String.format("Method:%s ExcelExportSign 注解标识有误:", name));
                    }
                    ExcelExportSign sign = method.getAnnotation(ExcelExportSign.class);
                    ExcelHeader header = new ExcelHeader(name, sign.title(), sign.order(), method.getGenericReturnType());

                    excelHeaders.add(header);
                    methodMap.put(name, method);
                }
            }
        }
        Collections.sort(excelHeaders);
    }

    private boolean isLegalGetOrIsMethod(Method method) {
        String name = method.getName();
        return name != null
                && (name.startsWith("get") || name.startsWith("is"))
                && method.getParameters().length == 0;
    }

    private List<Class<?>> getAllParentClazz(Class<?> clazz) {
        List<Class<?>> clazzs = new ArrayList<>();
        listAllParentClasses(clazz, clazzs);
        return clazzs;
    }

    private void listAllParentClasses(Class<?> clazz, List<Class<?>> clazzs) {
        clazzs.add(clazz);
        Class<?> parent = clazz.getSuperclass();
        if (parent != null) {
            listAllParentClasses(parent, clazzs);
        }
    }
}
