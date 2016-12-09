package com.ming.captcha;

import org.patchca.background.BackgroundFactory;
import org.patchca.color.GradientColorFactory;
import org.patchca.filter.predefined.*;
import org.patchca.service.Captcha;
import org.patchca.service.ConfigurableCaptchaService;
import org.patchca.text.renderer.RandomYBestFitTextRenderer;
import org.patchca.word.RandomWordFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * 验证码生成工具类
 * @author liuhengchong
 *
 */
public class CaptchaUtils {
	private static ConfigurableCaptchaService cs = new ConfigurableCaptchaService();
	private static RandomWordFactory randomWordFactory4 = new RandomWordFactory();
	private static DoubleRippleFilterFactory doubleRippleFilterFactory = new DoubleRippleFilterFactory();
	private static CurvesRippleFilterFactory curvesRippleFilterFactory;

	private static BackgroundFactory backgroundFactory = new RandomBackGroundFactory();
	private static Random random = new Random();
	static {

		randomWordFactory4.setMaxLength(4);
		randomWordFactory4.setMinLength(4);

		randomWordFactory4.setCharacters("ABSDEGKMNPWXabsdegkmnpwx23456789");
		//randomWordFactory4.setCharacters("A");
		//cs.setColorFactory(new SingleColorFactory(new Color(125, 60, 170)));
		//cs.setColorFactory(new SingleColorFactory(Color.BLACK));

		curvesRippleFilterFactory = new CurvesRippleFilterFactory(cs.getColorFactory());


	}

	public static Captcha getCaptchaWithFourChar() {
		cs.setColorFactory(new GradientColorFactory());
		cs.setWordFactory(randomWordFactory4);
		cs.setFilterFactory(doubleRippleFilterFactory);
		//cs.setTextRenderer(new SimpleTextRenderer());
		cs.setTextRenderer(new RandomYBestFitTextRenderer());
		//cs.setFilterFactory(new CustomFilterFilterFactory());
		cs.setBackgroundFactory(backgroundFactory);
		return cs.getCaptcha();
	}

	public static ConfigurableCaptchaService getNewCaptcha(){

		ConfigurableCaptchaService captchaService = new ConfigurableCaptchaService();
		//captchaService.setColorFactory(new GradientColorFactory());
		captchaService.setWordFactory(randomWordFactory4);
		captchaService.setFilterFactory(new DoubleRippleFilterFactory());
		captchaService.setTextRenderer(new RandomYBestFitTextRenderer());
		//cs.setFilterFactory(new CustomFilterFilterFactory());
		captchaService.setBackgroundFactory(new RandomBackGroundFactory());

		return captchaService;
	}


	public static void main(String[] args) throws IOException {

		ConfigurableCaptchaService captchaService = getNewCaptcha();
		for(int i =0 ;i<30;i++){
		//	captchaService.setTextRenderer(new RandomYBestFitTextRenderer());
			captchaService.setFilterFactory(new CustomFilterFilterFactory());

			Captcha	captcha = captchaService.getCaptcha();

			String value = captcha.getChallenge();
			BufferedImage image = captcha.getImage();

			File file = new File("E:\\Test\\captcha\\3\\"+value+"_"+random.nextInt(10000)+".png");
			//FileOutputStream imageOutStream = new FileOutputStream();
			ImageIO.write(image,"png",file);
		}


	}



}
