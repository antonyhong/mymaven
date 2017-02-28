package compress.simpleimage;

import com.alibaba.simpleimage.ImageRender;
import com.alibaba.simpleimage.SimpleImageException;
import com.alibaba.simpleimage.render.*;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by HONGYONGMING on 2017/2/27.
 */
public class SimpleImageTest {

    /***
     *
     * 运行代码有报错
     * */
    public static void main(String[] args) throws IOException {
        String srcDir= "E:\\技术中心\\jpg压缩\\图片测试\\原图";
        String destDir = "E:\\技术中心\\jpg压缩\\图片测试\\aliSI";
        File file = new File(srcDir);
        File[] files = file.listFiles();
        System.out.println("开始压缩图片...");
        long start = System.currentTimeMillis();
        for (File inFile : files){
            File outFile= new File(destDir+"\\"+inFile.getName());
            compress(inFile,outFile,0.7f);
        }
        System.out.println("压缩完成图片,耗时:"+(System.currentTimeMillis()-start));

    }

    private static void compress(File in,File out,float quality) throws IOException {
        //File input = new File("digital_image_processing.jpg");
        ScaleParameter scaleParam = new ScaleParameter(1024, 1024);  //将图像缩略到1024x1024以内，不足1024x1024则不做任何处理


        FileInputStream inStream = null;
        FileOutputStream outStream = null;
        WriteRender wr = null;
        try {
            inStream = new FileInputStream(in);
            outStream = new FileOutputStream(out);
            ImageRender rr = new ReadRender(inStream);
            ImageRender sr = new ScaleRender(rr, scaleParam);
            wr = new WriteRender(sr, outStream);

            wr.render();                            //触发图像处理
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(inStream);         //图片文件输入输出流必须记得关闭
            IOUtils.closeQuietly(outStream);
            if (wr != null) {
                try {
                    wr.dispose();                   //释放simpleImage的内部资源
                } catch (SimpleImageException ignore) {
                    // skip ...
                }
            }
        }
    }
}
