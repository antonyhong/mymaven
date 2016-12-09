package com.ming.captcha;

import org.patchca.background.BackgroundFactory;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Created by hongyongming on 2016/12/5.
 */
public class RandomBackGroundFactory implements BackgroundFactory {
    private Random random = new Random();

    public void fillBackground(BufferedImage image) {

        Graphics2D graphics = (Graphics2D) image.getGraphics();
        graphics.setStroke(new BasicStroke(3.0F + 2.0F * random.nextFloat()));

        // 验证码图片的宽高
        int imgWidth = image.getWidth();
        int imgHeight = image.getHeight();
        // 填充为白色背景
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, imgWidth, imgHeight);
        //addNoisyPoint(graphics, imgWidth, imgHeight);
        graphics.setColor(Color.BLACK);
        int confuseLineCount = 1 + random.nextInt(2);
        for (int i = 0; i < confuseLineCount; i++) {
            // 随机位置
            int xInt = random.nextInt(imgWidth / 2) + 10;
            int yInt = random.nextInt(imgHeight / 2) + 15;

            // 画干扰线
            int xInt2 = random.nextInt(imgWidth / 2) + imgWidth / 2;
            int yInt2 = random.nextInt(imgHeight / 2) + imgHeight / 2;
            graphics.drawLine(xInt, yInt, xInt2, yInt2);
        }
    }

    private void addNoisyPoint(Graphics2D graphics, int imgWidth, int imgHeight) {
        //  画200个噪点(颜色及位置随机)
        for (int i = 0; i < 40; i++) {
            // 随机颜色
//            int rInt = random.nextInt(255);
//            int gInt = random.nextInt(255);
//            int bInt = random.nextInt(255);

            int rInt = 255;
            int gInt = 255;
            int bInt = 255;

            graphics.setColor(new Color(rInt, gInt, bInt));

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
