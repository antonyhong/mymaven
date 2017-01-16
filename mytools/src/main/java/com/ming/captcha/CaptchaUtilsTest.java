package com.ming.captcha;

import com.ming.captcha.extra.DoNothingFilterFactory;
import com.ming.captcha.extra.OverloadTextRender;
import com.ming.captcha.extra.RandomColorFactory2;
import org.patchca.background.BackgroundFactory;
import org.patchca.color.ColorFactory;
import org.patchca.color.GradientColorFactory;
import org.patchca.color.RandomColorFactory;
import org.patchca.color.SingleColorFactory;
import org.patchca.filter.predefined.CurvesRippleFilterFactory;
import org.patchca.filter.predefined.DiffuseRippleFilterFactory;
import org.patchca.filter.predefined.MarbleRippleFilterFactory;
import org.patchca.service.Captcha;
import org.patchca.service.ConfigurableCaptchaService;
import org.patchca.text.renderer.BestFitTextRenderer;
import org.patchca.word.RandomWordFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * 验证码生成工具类
 *
 * @author hongyongming
 */
public class CaptchaUtilsTest {
    private static ConfigurableCaptchaService captchaService = new ConfigurableCaptchaService();
    private static RandomWordFactory randomWordFactory4 = new RandomWordFactory();

    private static BackgroundFactory backgroundFactory;
    private static Random random = new Random();

    static {

        //new RandomColorFactory2()
        backgroundFactory = new NoisyBackGroundFactory(0,1,new SingleColorFactory(), Color.white);
        randomWordFactory4.setMaxLength(4);
        randomWordFactory4.setMinLength(4);

        randomWordFactory4.setCharacters("ABSDEGKMNPWXabsdegkmnpwx23456789");
        captchaService.setWordFactory(randomWordFactory4);

        captchaService.setColorFactory(new SingleColorFactory());

        //  ConfigurableCaptchaService captchaService = new ConfigurableCaptchaService();
        //captchaService.setColorFactory(new GradientColorFactory());

        //curvesRippleFilterFactory = new CurvesRippleFilterFactory(captchaService.getColorFactory());
        //RandomYBestFitTextRenderer
        captchaService.setTextRenderer(new BestFitTextRenderer());
        //captchaService.setTextRenderer(new SimpleTextRenderer());
        //captchaService.setTextRenderer(new OverloadTextRender());
        //cs.setFilterFactory(new CustomFilterFilterFactory());
        captchaService.setBackgroundFactory(backgroundFactory);


    }

    public static Captcha getCaptcha() {
       //captchaService.setFilterFactory(new DoNothingFilterFactory());
        //captchaService.setFilterFactory(new CurvesRippleFilterFactory());
        //captchaService.setFilterFactory(new DiffuseRippleFilterFactory());
        captchaService.setFilterFactory(new CurvesRippleFilterFactory());

        //captchaService.setFilterFactory(new CurvesFilterFactory());
        return captchaService.getCaptcha();
    }



    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 10; i++) {
            Captcha captcha = getCaptcha();
            String value = captcha.getChallenge();
            BufferedImage image = captcha.getImage();
            File file = new File("E:\\Test\\captcha\\test\\" + value + "_" + random.nextInt(10000) + ".png");
            //FileOutputStream imageOutStream = new FileOutputStream();
            ImageIO.write(image, "png", file);
        }
    }




}
