package com.ming.excel.excelopr;


import org.apache.poi.ss.usermodel.Sheet;

import java.util.Collection;
import java.util.function.Function;

/**
 * Created by hongyongming on 2016/4/5.
 */
public interface IImportSheet<P> {

    String getSheetName();

    <T extends Sheet,R extends Collection<P>> Function<T,R> getReadSheetFunc();
}
