package com.ming.img;


import com.google.common.io.Files;
import org.im4java.core.ConvertCmd;
import org.im4java.core.GMOperation;
import org.im4java.process.Pipe;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.renderable.RenderableImage;
import java.io.*;

/**
 * Created by xuxiaoping on 2016/12/9.
 */
public class ImageUtils {


    public static void main(String[] args) throws Exception {
        String srcDir= "E:\\技术中心\\jpg压缩\\图片测试\\原图";
        String destDir = "E:\\技术中心\\jpg压缩\\图片测试\\qq";
        File file = new File(srcDir);
        File[] files = file.listFiles();
        for (File s : files){
            byte[] bytes = compressImage(new FileInputStream(s),null,"jpg");
            Files.write(bytes,new File(destDir+"\\"+s.getName()));
        }
    }


    public static byte[] compressImage(InputStream inputStream, Double quality,String format) throws Exception {
        GMOperation op = new GMOperation();
        op.addImage("-");
        if(quality!=null) {
            op.quality(quality);
        }else{
            op.addRawArgs("-define", "jpeg:preserve-settings");
        }
        op.addRawArgs("-strip");
        op.addImage(format+":-");
        Pipe pipeIn = new Pipe(inputStream, null);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Pipe pipeOut = new Pipe(null, outputStream);
        // set up command
        ConvertCmd convert = new ConvertCmd(true);
        convert.setInputProvider(pipeIn);
        convert.setOutputConsumer(pipeOut);
        convert.run(op);
        return outputStream.toByteArray();
    }



}
