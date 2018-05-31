package com.ryan.baidu.excel.excelopr;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.List;

/**
 * Created by hongyongming on 2016/4/1.
 */
public class MyWorkBook {
    private final Logger logger = LoggerFactory.getLogger(MyWorkBook.class);

    private Workbook workbook;

    public MyWorkBook() {
        workbook = new HSSFWorkbook();
    };

    public MyWorkBook(Workbook workbook) {

        this.workbook = workbook;
    }

    public void flush(OutputStream stream) {
        if (workbook != null) {
            try {
                workbook.write(stream);
                stream.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    stream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }


    public MyWorkBook addSheet(IExportSheet define) {
        Sheet sheet = workbook.getSheet(define.getSheetName());
        if (sheet == null) {
            sheet = workbook.createSheet(define.getSheetName());
        }
        define.getFillSheetFunc().accept(sheet);
        return this;
    }

    /** todo */
    public MyWorkBook addSheet(Class<?> clazz, List<?> entities, String sheetName, boolean initHeader) {
        ExportSheet exportSheet = new ExportSheet(sheetName, entities, initHeader, ExportSheet.DEFAULT_START_ROW_INDEX);
        return addSheet(exportSheet);
    }

    public <O> Collection<O> readSheet(IImportSheet<O> iImportSheet){
        Sheet sheet = workbook.getSheet(iImportSheet.getSheetName());
        if (sheet == null) {
            sheet = workbook.getSheetAt(0);
        }
        return  iImportSheet.getReadSheetFunc().apply(sheet);
    }

}
