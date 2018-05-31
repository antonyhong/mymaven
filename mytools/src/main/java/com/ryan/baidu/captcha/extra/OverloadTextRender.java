package com.ryan.baidu.captcha.extra;

import org.patchca.text.renderer.AbstractTextRenderer;
import org.patchca.text.renderer.TextCharacter;
import org.patchca.text.renderer.TextString;

import java.util.Iterator;
import java.util.Random;

/**
 * Created by hongyongming on 2016/12/12.
 */
public class OverloadTextRender extends AbstractTextRenderer {

    public OverloadTextRender() {
        this.leftMargin = 25;
    }

    @Override
    protected void arrangeCharacters(int width, int height, TextString ts) {

        double widthRemaining = ((double) width - ts.getWidth() - (double) this.leftMargin - (double) this.rightMargin) / (double) ts.getCharacters().size();
        double x = (double) this.leftMargin;
        double vmiddle = (double) (height / 2);
        Random r = new Random();
        int distance;
        TextCharacter tc;
        for (Iterator var6 = ts.getCharacters().iterator(); var6.hasNext(); x += (tc.getWidth() - distance)) {
            tc = (TextCharacter) var6.next();
            //double y = (double)this.topMargin + ((double)height + tc.getAscent() * 0.7D) / 2.0D;
            double heightRemaining = (double) height - tc.getHeight();
            double y = vmiddle + 0.35D * tc.getAscent() + (1.0D - 2.0D * r.nextDouble()) * heightRemaining;
            tc.setX(x);
            tc.setY(y);
            distance = 8 + r.nextInt(5);
        }


    }
}
