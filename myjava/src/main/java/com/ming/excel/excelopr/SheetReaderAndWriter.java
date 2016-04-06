package com.ming.excel.excelopr;

import org.apache.poi.hssf.usermodel.HSSFPictureData;
import org.apache.poi.ss.usermodel.*;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

/**
 * Created by hongyongming on 2016/4/5.
 */
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
//        if (method.getName().toLowerCase().contains("img") && pictureIndexCounter < pictures.size()) {
//            HSSFPictureData pictureData = pictures.get(pictureIndexCounter++);
//            byte[] imgBytes = pictureData.getData();
//            Result<String> res = UploadService.getIUploadService(serviceUrl).uploadItemImage(imgBytes);
//            if (res.success()) {
//                method.invoke(entity, res.getValue());
//            } else {
//                //logger.error("上传图片失败");
//                throw new RuntimeException("上传图片失败");
//            }
//        } else if (cell == null || FourbUtil.isBlankStr(cell.toString())) {
//            return pictureIndexCounter;
//        } else
        if (type.equals("class java.util.Date")) {
            method.invoke(entity, cell.getDateCellValue());
        } else if (type.equals("class java.lang.Boolean") || type.equals("boolean")) {
            cell.setCellType(Cell.CELL_TYPE_BOOLEAN);
            method.invoke(entity, cell.getBooleanCellValue());
        } else if (type.equals("class java.lang.Integer") || type.equals("int")) {
            cell.setCellType(Cell.CELL_TYPE_NUMERIC);
            method.invoke(entity, (int) cell.getNumericCellValue());
        } else if (type.equals("class java.lang.Double") || type.equals("double")) {
            cell.setCellType(Cell.CELL_TYPE_NUMERIC);
            method.invoke(entity, cell.getNumericCellValue());
        } else if (type.equals("class java.lang.Long") || type.equals("long")) {
            cell.setCellType(Cell.CELL_TYPE_NUMERIC);
            method.invoke(entity, (long) cell.getNumericCellValue());
        } else {
            cell.setCellType(Cell.CELL_TYPE_STRING);
            method.invoke(entity, cell.getStringCellValue());
        }
        return pictureIndexCounter;
    }
}
