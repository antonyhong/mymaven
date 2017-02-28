package compress;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.imageio.stream.MemoryCacheImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Iterator;

/**
 * Created by HONGYONGMING on 2017/2/24.
 *
 * @see http://javabeat.net/java-compress-image/
 */
public class ImgCompression {

    public static void main(String[] args) throws IOException {

        String srcDir= "E:\\技术中心\\jpg压缩\\图片测试\\原图";
        String destDir = "E:\\技术中心\\jpg压缩\\图片测试\\java60";
        File file = new File(srcDir);
        File[] files = file.listFiles();
        System.out.println("开始压缩图片...");
        long start = System.currentTimeMillis();
        for (File inFile : files){
            File outFile= new File(destDir+"\\"+inFile.getName());
            compress(inFile,outFile,0.7f,getExtName(inFile.getName(),'.'));
        }
        System.out.println("压缩完成图片,耗时:"+(System.currentTimeMillis()-start));
    }

    private static String getExtName(String s, char split) {
        int i = s.indexOf(split);
        int leg = s.length();
        return (i > 0 ? (i + 1) == leg ? " " : s.substring(i+1, s.length()) : " ");
    }

    private static void compress(File inImage,File outImage,float quality,String suffix) throws IOException {
        //File input = new File("digital_image_processing.jpg");

        BufferedImage bufferedImage = ImageIO.read(inImage);

        //File compressedImageFile = new File("compress.jpg");
        OutputStream os =new FileOutputStream(outImage);

        Iterator<ImageWriter> writers =  ImageIO.getImageWritersByFormatName(suffix);
        ImageWriter imageWriter = (ImageWriter) writers.next();

        ImageOutputStream ios = ImageIO.createImageOutputStream(os);
        imageWriter.setOutput(ios);

        ImageWriteParam param = imageWriter.getDefaultWriteParam();
        if(param.canWriteCompressed()){
            param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            param.setCompressionQuality(quality);
        }
        imageWriter.write(null, new IIOImage(bufferedImage, null, null), param);
        os.close();
        ios.close();
        imageWriter.dispose();
    }


    private static void compress2(File inImage,File outImage,float quality) throws IOException {
        //File input = new File("digital_image_processing.jpg");

        BufferedImage bufferedImage = ImageIO.read(inImage);
        //File compressedImageFile = new File("compress.jpg");
        OutputStream os =new FileOutputStream(outImage);
        compressImage(bufferedImage,os,quality);
        os.flush();
        os.close();
    }


    public static void compressImage(BufferedImage bufferedImage,OutputStream outputStream,float quality) throws IOException {
        Iterator<ImageWriter> iterator = ImageIO.getImageWritersByFormatName("jpg");
        ImageWriter imageWriter = iterator.next();
        ImageWriteParam imageWriteParam = imageWriter.getDefaultWriteParam();
        if(imageWriteParam.canWriteCompressed()){
            imageWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            imageWriteParam.setCompressionQuality(quality);
        }
        ImageOutputStream imageOutputStream =new MemoryCacheImageOutputStream(outputStream);
        imageWriter.setOutput(imageOutputStream);
        IIOImage iioimage = new IIOImage(bufferedImage, null, null);
        imageWriter.write(null, iioimage, imageWriteParam);
        imageOutputStream.flush();

        imageOutputStream.close();
        imageWriter.dispose();
    }
}
