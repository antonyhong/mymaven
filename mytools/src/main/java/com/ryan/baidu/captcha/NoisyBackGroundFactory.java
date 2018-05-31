package com.ryan.baidu.captcha;

import org.patchca.background.BackgroundFactory;
import org.patchca.color.ColorFactory;
import org.patchca.color.SingleColorFactory;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Created by hongyongming on 2016/12/5.
 */
public class NoisyBackGroundFactory implements BackgroundFactory {
    private Random random = new Random();
    private ColorFactory colorFactory;
    private Color backGroundColor;
    private int noisyPointCount;
    private int noisyLineCont;

    /**
     * 干扰线会随机添加 0-2条。
     * */
    public NoisyBackGroundFactory(int noisyPointCount, int noisyLineCont,ColorFactory colorFactory,Color backGroundColor) {
        this.colorFactory = colorFactory;
        this.noisyPointCount = noisyPointCount;
        this.noisyLineCont = noisyLineCont;
        this.backGroundColor =backGroundColor;
    }
    public NoisyBackGroundFactory(int noisyPointCount, int noisyLineCont) {
       this( noisyPointCount, noisyLineCont,new SingleColorFactory(),Color.WHITE) ;
    }

    public void fillBackground(BufferedImage image) {
        Graphics2D graphics = (Graphics2D) image.getGraphics();
        graphics.setStroke(new BasicStroke(3.0F + 2.0F * random.nextFloat()));
        // 验证码图片的宽高
        int imgWidth = image.getWidth();
        int imgHeight = image.getHeight();
        // 填充为白色背景
        graphics.setColor(backGroundColor);
        graphics.fillRect(0, 0, imgWidth, imgHeight);
        addNoisyPoint(noisyPointCount,this.colorFactory,graphics, imgWidth, imgHeight);
        addNoisyLine(noisyLineCont+random.nextInt(2), this.colorFactory, graphics, imgWidth, imgHeight);
    }

    private void addNoisyLine(int noisyLineCont,ColorFactory colorFactory,Graphics2D graphics, int imgWidth, int imgHeight) {
        for (int i = 0; i < noisyLineCont; i++) {
            graphics.setColor(colorFactory.getColor(i));
            // 随机位置
            int xInt = random.nextInt(imgWidth / 2) + 10;
            int yInt = random.nextInt(imgHeight / 2) + 15;

            // 画干扰线
            int xInt2 = random.nextInt(imgWidth / 2) + imgWidth / 2;
            int yInt2 = random.nextInt(imgHeight / 2) + imgHeight / 2;
            graphics.drawLine(xInt, yInt, xInt2, yInt2);
        }
    }
    private void addNoisyPoint(int pointCount,ColorFactory colorFactory,Graphics2D graphics, int imgWidth, int imgHeight) {
        for (int i = 0; i < pointCount; i++) {

            graphics.setColor(colorFactory.getColor(i));
            // 随机位置
            int xInt = random.nextInt(imgWidth - 3);
            int yInt = random.nextInt(imgHeight - 2);

            // 随机旋转角度
            int sAngleInt = random.nextInt(360);
            int eAngleInt = random.nextInt(360);
            // 随机大小
            int wInt = random.nextInt(10);
            int hInt = random.nextInt(10);
            graphics.fillArc(xInt, yInt, wInt, hInt, sAngleInt, eAngleInt);
        }
    }
}
