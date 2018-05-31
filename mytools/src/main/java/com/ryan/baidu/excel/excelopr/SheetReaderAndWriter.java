package com.ryan.baidu.excel.excelopr;

import com.ryan.baidu.utils.DateUtils;
import com.ryan.baidu.utils.CommonUtil;
import org.apache.poi.hssf.usermodel.HSSFPictureData;
import org.apache.poi.ss.usermodel.*;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class SheetReaderAndWriter {

    protected void setCellVal(Sheet sheet, Row row, Method method, Cell cell, Object val, String type) throws Exception {
        if (type.equals("class java.util.Date")) {
            setDateVal(sheet, val, cell);
        } else if (type.equals("class java.lang.Boolean") || type.equals("boolean")) {
            cell.setCellValue((Boolean) val);
        } else if (type.equals("class java.lang.Integer") || type.equals("int")) {
            cell.setCellValue((Integer) val);
        } else if (type.equals("class java.lang.Double") || type.equals("double")) {
            cell.setCellValue((Double) val);
        } else if (type.equals("class java.lang.Long") || type.equals("long")) {
            cell.setCellValue((Long) val);
        } else {
            if (method.getName().toLowerCase().contains("img")) {
                cell.setCellValue((String) val);
                //setImgVal(row, val, cell);
            } else {
                cell.setCellValue((String) val);
            }
        }
    }

    private void setDateVal(Sheet sheet, Object val, Cell cell) throws Exception {
        Workbook workbook = sheet.getWorkbook();
        Date d = (Date) val;
        CellStyle style = workbook.createCellStyle();
        short df = workbook.createDataFormat().getFormat("yyyy-MM-dd HH:mm:ss");
        style.setDataFormat(df);
        style.setWrapText(false);
        //sheet.setColumnWidth(cell.getColumnIndex(), 4500);
        if (d != null) {
            cell.setCellValue(d);
        }
        cell.setCellStyle(style);
    }

    protected Class<?> getFieldType(String type) {
        if (type.equals("class java.util.Date")) {
            return Date.class;
        } else if (type.equals("class java.lang.Boolean")) {
            return Boolean.class;
        } else if (type.equals("boolean")) {
            return Boolean.TYPE;
        } else if (type.equals("class java.lang.Integer")) {
            return Integer.class;
        } else if (type.equals("int")) {
            return Integer.TYPE;
        } else if (type.equals("class java.lang.Double")) {
            return Double.class;
        } else if (type.equals("double")) {
            return Double.TYPE;
        } else if (type.equals("class java.lang.Long")) {
            return Long.class;
        } else if (type.equals("long")) {
            return Long.TYPE;
        }
        return String.class;
    }

    protected <T> int setEntityVal(String serviceUrl, String type, List<HSSFPictureData> pictures, int pictureIndexCounter,
                                   T entity, Cell cell, Method method) throws Exception {

        if (cell == null || CommonUtil.isBlankStr(cell.toString())) {
            return pictureIndexCounter;
        } else {
            Object cellValue = getCellObjValue(type, cell);
            method.invoke(entity, cellValue);
        }

        return pictureIndexCounter;
    }


    private Object getCellObjValue(String type, Cell cell) {
        Objects.requireNonNull(cell);
        Object cellValue;
        String strCellValue = cell.toString();

        if (type.equals("class java.util.Date")) {
            cellValue = cell.getCellType() == Cell.CELL_TYPE_NUMERIC?
                    cell.getDateCellValue():
                    DateUtils.parseAllFormat(strCellValue);
        } else if (type.equals("class java.lang.Boolean") || type.equals("boolean")) {
            cellValue = CommonUtil.toBoolean(strCellValue, null);
        } else if (type.equals("class java.lang.Integer") || type.equals("int")) {
            cellValue = CommonUtil.toInt(strCellValue, null);
        } else if (type.equals("class java.lang.Double") || type.equals("double")) {
            cellValue = CommonUtil.toDouble(strCellValue, null);
        } else if (type.equals("class java.lang.Long") || type.equals("long")) {
            cellValue = CommonUtil.toLong(strCellValue, null);
        } else {
            cellValue = strCellValue;
        }

        if (cellValue == null) {
            throw new RuntimeException(String.format("单元格%s 转成成 %s失败", strCellValue, type));
        }
        return cellValue;
    }

}
