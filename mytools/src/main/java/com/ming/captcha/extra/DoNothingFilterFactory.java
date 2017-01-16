package com.ming.captcha.extra;

import org.patchca.filter.AbstractFilterFactory;

import java.awt.image.BufferedImageOp;
import java.util.Collections;
import java.util.List;

/**
 * Created by hongyongming on 2016/12/12.
 */
public class DoNothingFilterFactory extends AbstractFilterFactory {
    @Override
    protected List<BufferedImageOp> getFilters() {
        return Collections.emptyList();
    }
}
