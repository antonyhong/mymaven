package com.ming.excel;


import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import com.ming.utils.DateUtils;
import com.ming.utils.StringUtil;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;


public class ExcelIO {

    private final Logger logger = LoggerFactory.getLogger(ExcelIO.class);


    public final static ThreadLocal<ExcelIO> INSTANCE = new ThreadLocal<ExcelIO>() {
        @Override
        protected ExcelIO initialValue() {
            return new ExcelIO();
        }
    };
    private BiMap<String, String> header;
    private String sheetName = "Sheet";
    private final int MaxRowsPerSheet = 65536;
    private ExcelIO() {

    }

    public ExcelIO setHeader(Map<String, String> header) {
        this.header = ImmutableBiMap.copyOf(header);
        return this;
    }

    public ExcelIO setSheetName(String sheetName) {
        if (sheetName != null) {
            this.sheetName = sheetName;
        }
        return this;
    }

    public void exportTo(OutputStream outputStream, Collection<?> datas) {
        checkNotNull(this.header);
        try {
            @SuppressWarnings("resource")
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = null;
            int rowCount = 0;
            int sheetCount =0;
            for (Object item : datas) {
                //如果是新的sheet
                if (rowCount >= MaxRowsPerSheet || sheet == null ) {
                    rowCount = 0;
                    sheet = wb.createSheet();
                    wb.setSheetName(sheetCount, this.sheetName + (++sheetCount));
                    // header
                    Row headerRow = sheet.createRow(rowCount++);
                    int headerCellCount = 0;
                    for (String key : this.header.keySet()) {
                        Cell cell = headerRow.createCell(headerCellCount++);
                        cell.setCellValue(this.header.get(key));
                    }
                }
                Map<String,Object> valueMap = BeanUtils.beanToMap(item);
                Row row = sheet.createRow(rowCount++);
                int cellCount = 0;
                for (String key : this.header.keySet()) {
                    Cell cell = row.createCell(cellCount++);
                    int columnIndex = cell.getColumnIndex();
                    sheet.setColumnWidth(columnIndex, 3000);
                    cell.setCellValue(getStrValue(valueMap,key));
                }
            }
            wb.write(outputStream);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    private String getStrValue(Map<String,Object> valueMap,String key){
        Object obj = valueMap.get(key);

        if (obj == null) {
            return "";
        }
        if (obj instanceof Date) {
            return DateUtils.formatYmdhms((Date) obj);
        }
        return StringUtil.toString(obj);
    }


    public Collection<? extends Map<String, Object>> importFrom(InputStream inputStream, String serverName) throws Exception {
        checkNotNull(this.header);
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        @SuppressWarnings("resource")
        HSSFWorkbook wb = new HSSFWorkbook(inputStream);
        Sheet sheet = wb.getSheetAt(0);
        Row headerRow = sheet.getRow(0);
        for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {
            Map<String, Object> map = new LinkedHashMap<String, Object>();
            Row row = sheet.getRow(i);
            for (int j = 0; j < headerRow.getLastCellNum(); j++) {
                String headerValue = headerRow.getCell(j).getStringCellValue();
                String headerKey = this.header.inverse().get(headerValue);
                Cell cell = row.getCell(j, Row.CREATE_NULL_AS_BLANK);
                if (cell.getCellType() != Cell.CELL_TYPE_STRING) {
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                }
                String cellValue = cell.toString();
                map.put(headerKey, cellValue);
            }
            result.add(map);
        }
        return result;
    }

    private void checkNotNull(Object... objects) {
        for (Object object : objects) {
            if (object == null) {
                logger.error("ExcelIO failed. Expecting non-null argument.");
                throw new IllegalArgumentException("Argument should not be null!");
            }
        }
    }

}
