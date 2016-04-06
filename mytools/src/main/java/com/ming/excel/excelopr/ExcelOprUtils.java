package com.ming.excel.excelopr;

import com.google.common.base.Strings;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by hongyongming on 2016/4/1.
 */
public class ExcelOprUtils {

    public static void export(HttpServletResponse response, String fileName, List<?> entities) throws IOException {
        export(response, fileName, null, new ExportSheet(entities));
    }

    public static void export(HttpServletResponse response, String fileName, String templatePath, IExportSheet iExportSheet) throws IOException {
        export(response, fileName, templatePath, Arrays.asList(iExportSheet));
    }

    public static void export(HttpServletResponse response, String fileName, String templatePath, List<IExportSheet> erpExportSheets) throws IOException {
        setExportResponseContentType(response, fileName);
        export(response.getOutputStream(),templatePath,erpExportSheets);
    }
    public  static void export(OutputStream outputStream, String templatePath, List<IExportSheet> erpExportSheets) throws IOException {
        MyWorkBook workBook;
        if (!Strings.isNullOrEmpty(templatePath)) {
            File file = new File(templatePath);
            if (!file.exists()) {
                throw new RuntimeException("找不到模板文件:" + templatePath);
            }
            HSSFWorkbook hssfWorkbook = new HSSFWorkbook(new FileInputStream(new File(templatePath)));
            workBook = new MyWorkBook(hssfWorkbook);
        } else {
            workBook = new MyWorkBook();
        }
        erpExportSheets.forEach(workBook::addSheet);
        workBook.flush(outputStream);
    }

    public static <O> Collection<O> readExcel(InputStream inputStream, IImportSheet<O> iImportSheet) throws IOException {
        MyWorkBook workBook = new MyWorkBook(new HSSFWorkbook(inputStream));
        return workBook.readSheet(iImportSheet);
    }

    private static void setExportResponseContentType(HttpServletResponse response, String fileName) throws UnsupportedEncodingException {
        fileName = assureExcelSuffix(fileName);
        response.setContentType("application/msexcel");
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\";", URLEncoder.encode(fileName, "utf-8")));
    }

    private static String assureExcelSuffix(String fileName) {
        if (!fileName.endsWith(".xls") && !fileName.endsWith(".xlsx")) {
            fileName = fileName + ".xls";
        }
        return fileName;
    }

}
