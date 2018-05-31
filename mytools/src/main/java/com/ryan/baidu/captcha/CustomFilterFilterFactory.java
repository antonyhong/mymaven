package com.ryan.baidu.captcha;

import org.patchca.color.ColorFactory;
import org.patchca.filter.library.*;
import org.patchca.filter.predefined.RippleFilterFactory;

import java.awt.image.BufferedImageOp;
import java.util.List;

/**
 * Created by hongyongming on 2016/12/5.
 */
public class CustomFilterFilterFactory extends RippleFilterFactory {

    protected MarbleImageOp marble = new MarbleImageOp();
    protected DiffuseImageOp diffuse = new DiffuseImageOp();
    protected CurvesImageOp curves = new CurvesImageOp();
    protected WobbleImageOp wobble = new WobbleImageOp();
    protected DoubleRippleImageOp ripple = new DoubleRippleImageOp();

    @Override
    protected List<BufferedImageOp> getPreRippleFilters() {
        List<BufferedImageOp> list = super.getPreRippleFilters();
        list.add(curves);
        //list.add(diffuse);
        //list.add(marble);
        list.add(ripple);
        return list;
    }

    public void setStrokeMin(float strokeMin) {
        this.curves.setStrokeMin(strokeMin);
    }

    public void setStrokeMax(float strokeMax) {
        this.curves.setStrokeMax(strokeMax);
    }

    public void setColorFactory(ColorFactory colorFactory) {
        this.curves.setColorFactory(colorFactory);
    }


}
