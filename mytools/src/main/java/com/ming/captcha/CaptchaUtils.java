package com.ming.captcha;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import org.patchca.background.BackgroundFactory;
import org.patchca.color.ColorFactory;
import org.patchca.color.SingleColorFactory;
import org.patchca.filter.library.CurvesImageOp;
import org.patchca.filter.library.DoubleRippleImageOp;
import org.patchca.filter.predefined.CurvesRippleFilterFactory;
import org.patchca.filter.predefined.DiffuseRippleFilterFactory;
import org.patchca.filter.predefined.DoubleRippleFilterFactory;
import org.patchca.filter.predefined.MarbleRippleFilterFactory;
import org.patchca.filter.predefined.RippleFilterFactory;
import org.patchca.filter.predefined.WobbleRippleFilterFactory;
import org.patchca.service.Captcha;
import org.patchca.service.ConfigurableCaptchaService;
import org.patchca.word.RandomWordFactory;

/**
 * 验证码生成工具类
 * @author hongyongming
 *
 */
public class CaptchaUtils {
	private static ConfigurableCaptchaService cs = new ConfigurableCaptchaService();
	private static RandomWordFactory randomWordFactory4 = new RandomWordFactory();

	private static Random random = new Random();
	static {
		randomWordFactory4.setMaxLength(4);
		randomWordFactory4.setMinLength(4);
		randomWordFactory4.setCharacters("ABSDEGKMNPWXabsdegkmnpwx23456789");
		//randomWordFactory4.setCharacters("A");
		cs.setColorFactory(new SingleColorFactory(Color.BLACK));
	}
	

	public static Captcha getCaptchaWithFourChar() {
		cs.setWordFactory(randomWordFactory4);
//		cs.setFilterFactory(doubleRippleFilterFactory);
		cs.setFilterFactory(new CustomFilterFactory());
		cs.setBackgroundFactory(new NoisyLineBackGroundFactory());
		return cs.getCaptcha();
	}
	

	public static class CustomFilterFactory extends RippleFilterFactory {
	    //protected MarbleImageOp marble = new MarbleImageOp();
		//protected WobbleImageOp wobble = new WobbleImageOp();
		//protected DiffuseImageOp diffuse = new DiffuseImageOp();
	    protected CurvesImageOp curves = new CurvesImageOp();
	    protected DoubleRippleImageOp ripple = new DoubleRippleImageOp();

	    @Override
	    protected List<BufferedImageOp> getPreRippleFilters() {
	        List<BufferedImageOp> list = super.getPreRippleFilters();
	        list.add(curves);
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
	
	/**添加干扰*/
	public static class NoisyLineBackGroundFactory implements BackgroundFactory {
	    private Random random = new Random();

	    public void fillBackground(BufferedImage image) {
	        Graphics2D graphics = (Graphics2D) image.getGraphics();
	        graphics.setStroke(new BasicStroke(3.0F + 2.0F * random.nextFloat()));

	        // 验证码图片的宽高
	        int imgWidth = image.getWidth();
	        int imgHeight = image.getHeight();
	        // 填充为白色背
	        graphics.setColor(Color.WHITE);
	        graphics.fillRect(0, 0, imgWidth, imgHeight);
	        //addNoisyPoint(graphics, imgWidth, imgHeight);
	        graphics.setColor(Color.BLACK);
	        //随机添加1 至 4 条干扰线
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

	}
	
	
	public static void main(String[] args) throws IOException{

		for(int i =0 ;i<30;i++){
		//	captchaService.setTextRenderer(new RandomYBestFitTextRenderer());
			Captcha	captcha = CaptchaUtils.getCaptchaWithFourChar();

			String value = captcha.getChallenge();
			BufferedImage image = captcha.getImage();

			File file = new File("E:\\Test\\captcha\\wenta\\"+value+"_"+random.nextInt(10000)+".png");
			//FileOutputStream imageOutStream = new FileOutputStream();
			ImageIO.write(image,"png",file);
		}

	}
	
	
}
