package com.ryan.baidu.excel.excel;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by kemeichai on 2015/9/16.
 */
public class ExcelOprUtil {

    public static void exportExcel(HttpServletResponse response, ExportExcelParam excel) throws IOException {
        String fileName = assureExcelSuffix(excel.fileName);
        response.setContentType("application/msexcel");
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\";", URLEncoder.encode(fileName, "utf-8")));
        ServletOutputStream outputStream = response.getOutputStream();
        ExcelIO.INSTANCE.get().setHeader(excel.headers).setSheetName(excel.sheetName).exportTo(outputStream, excel.dataList);
        outputStream.flush();
        outputStream.close();
    }

    public static void exportExcel(ExportExcelParam exportParam) throws IOException {
        String fileName = assureExcelSuffix(exportParam.fileName);
        FileOutputStream outputStream = new FileOutputStream(fileName);
        ExcelIO.INSTANCE.get().setHeader(exportParam.headers).setSheetName(exportParam.sheetName).exportTo(outputStream, exportParam.dataList);
        outputStream.flush();
        outputStream.close();
    }

    public static String assureExcelSuffix(String fileName) {
        if (!fileName.endsWith(".xls") && !fileName.endsWith(".xlsx")) {
            fileName = fileName + ".xls";
        }
        return fileName;
    }

    public static class ExportExcelParam {
        String fileName;
        String sheetName;
        LinkedHashMap headers;
        List<?> dataList;

        public ExportExcelParam(List<?> dataList, LinkedHashMap headers, String fileName, String sheetName) {
            this.dataList = dataList;
            this.fileName = fileName;
            this.headers = headers;
            this.sheetName = sheetName;
        }
    }
}
