package com.ming.captcha.extra;

import org.patchca.color.ColorFactory;

import java.awt.*;
import java.util.Random;

/**
 * Created by hongyongming on 2016/12/14.
 */
public class RandomColorFactory2 implements ColorFactory {
    private Color min = new Color(0, 0, 0);
    private Color max = new Color(255, 255, 255);
    Random r = new Random();
    public RandomColorFactory2() {
    }

    public void setMin(Color min) {
        this.min = min;
    }

    public void setMax(Color max) {
        this.max = max;
    }

    public Color getColor(int index) {

        return new Color(
                this.min.getRed() + r.nextInt(this.max.getRed() - this.min.getRed()),
                this.min.getGreen() + r.nextInt(this.max.getGreen() - this.min.getGreen()),
                this.min.getBlue() + r.nextInt(this.max.getBlue() - this.min.getBlue()));

    }
}