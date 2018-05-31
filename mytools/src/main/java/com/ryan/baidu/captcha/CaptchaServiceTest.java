package com.ryan.baidu.captcha;

import org.patchca.service.Captcha;
import org.patchca.service.CaptchaService;
import org.patchca.service.ConfigurableCaptchaService;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by hongyongming on 2016/12/13.
 */
public class CaptchaServiceTest {
    public static void main(String[] args) throws IOException {
        CaptchaService captchaService = new ConfigurableCaptchaService();
        Captcha captcha = captchaService.getCaptcha();
        String key = captcha.getChallenge();
        BufferedImage image = captcha.getImage();
        ImageIO.write(image, "png", new File("/captcha_" + key + ".png"));
    }

}
