package com.ryan.baidu.excel.excelopr;

import org.apache.poi.ss.usermodel.Sheet;

import java.util.function.Consumer;

/**
 * Created by hongyongming on 2016/4/5.
 */
public interface IExportSheet {

    String getSheetName();

    Consumer<Sheet> getFillSheetFunc();

}
