package com.ryan.baidu.captcha.extra;

import org.patchca.filter.AbstractFilterFactory;
import org.patchca.filter.library.CurvesImageOp;

import java.awt.image.BufferedImageOp;
import java.util.Arrays;
import java.util.List;

/**
 * Created by hongyongming on 2016/12/12.
 */
public class CurvesFilterFactory  extends AbstractFilterFactory {
    protected CurvesImageOp curves = new CurvesImageOp();

    @Override
    protected List<BufferedImageOp> getFilters() {
        return Arrays.asList(curves);
    }
}
