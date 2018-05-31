package com.ryan.baidu.excel.excelopr;

import org.apache.poi.hssf.usermodel.HSSFPictureData;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Function;

/**
 * Created by hongyongming on 2016/4/5.
 *
 */
public class ImportSheet<P> extends SheetReaderAndWriter implements IImportSheet<P> {
    private String sheetName;
    private Class<P> clazz;
    private String serviceUrl;

    private final Logger logger = LoggerFactory.getLogger(ImportSheet.class);

    public ImportSheet(String sheetName, Class<P> clazz, String serviceUrl) {
        this.sheetName = sheetName;
        this.clazz = clazz;
        this.serviceUrl = serviceUrl;
    }

    @Override
    public String getSheetName() {
        return sheetName;
    }

    @Override
    public <T extends Sheet, R extends Collection<P>> Function<T, R> getReadSheetFunc() {
        return sheet -> {
            HSSFWorkbook workbook =(HSSFWorkbook)sheet.getWorkbook();
            List<P> entitys = new ArrayList<>();
            Map<Integer, ExcelHeader> headers = readHeader(sheet.getRow(0), clazz);
            int endRowNum = sheet.getLastRowNum() + 1;
            String type;
            try {
                List<HSSFPictureData> pictures = workbook.getAllPictures();
                int picIndex = 0;
                for (int i = 1; i < endRowNum; i++) {
                    Row row = sheet.getRow(i);
                    if (row == null) {
                        continue;
                    }
                    P entity = clazz.newInstance(); //反射new对象(要有空的构造方法)
                    for (int index : headers.keySet()) {
                        Cell cell = row.getCell(index);
                        ExcelHeader header = headers.get(index); //根据readHeader方法的映射关系获取对应的实体属性关系
                        try {
                            type = header.getType().toString();
                            Method method = clazz.getDeclaredMethod(header.getMethodName(), getFieldType(type));
                            picIndex = setEntityVal(serviceUrl, type, pictures, picIndex, entity, cell, method);
                        } catch (Exception e) {
                            throw  new RuntimeException(e);
                            /**一旦失败就整个操作失败*/
                            //continue;
                        }
                    }
                    entitys.add(entity);
                }
            } catch (Exception e) {
                throw  new RuntimeException(e);
            }
            return (R)entitys;
        };
    }

    private Map<Integer, ExcelHeader> readHeader(Row row, Class<?> clazz) {
        List<ExcelHeader> headers = getImportHeader(clazz);
        Map<Integer, ExcelHeader> headerMap = new LinkedHashMap<>();
        String value;
        for (Cell cell : row) {
            if (cell.getCellType() != Cell.CELL_TYPE_STRING) continue;
            value = cell.getStringCellValue().trim();
            for (ExcelHeader header : headers) {
                if (header.getTitle().equals(value)) {
                    headerMap.put(cell.getColumnIndex(), header);
                    break;
                }
            }
        }
        return headerMap;
    }

    private List<ExcelHeader> getImportHeader(Class<?> clazz) {
        List<Class<?>> clazzs = getAllParentClazz(clazz);
        List<ExcelHeader> headers = new ArrayList<>();
        for (Class<?> c : clazzs) {
            Method[] methods = c.getDeclaredMethods();
            String name;
            for (Method method : methods) {
                name = method.getName();
                if (name != null && (name.startsWith("set"))) {
                    if (method.isAnnotationPresent(ExcelImportSign.class)) {
                        ExcelImportSign sign = method.getAnnotation(ExcelImportSign.class);
                        ExcelHeader header = new ExcelHeader(name, sign.title(), method.getParameterTypes()[0]);
                        headers.add(header);
                    }
                }
            }
        }
        return headers;
    }
    private List<Class<?>> getAllParentClazz(Class<?> clazz) {
        List<Class<?>> clazzs = new ArrayList<>();
        listAllParentClasses(clazz, clazzs);
        return clazzs;
    }

    private static void listAllParentClasses(Class<?> clazz, List<Class<?>> clazzs) {
        clazzs.add(clazz);
        Class<?> parent = clazz.getSuperclass();
        if (parent != null) {
            listAllParentClasses(parent, clazzs);
        }
    }
}
