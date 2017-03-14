package compress.gm;

import com.sun.javafx.binding.StringFormatter;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;

/**
 * Created by HONGYONGMING on 2017/2/28.
 */
public class GMTest {
    public static void main(String[] args) throws Exception {
        String srcDir= "E:\\技术中心\\jpg压缩\\图片测试\\原图";
        String destDir = "E:\\技术中心\\jpg压缩\\图片测试\\gmGrad2";

       // for(int i =0 ;i<20;i++){
            File file = new File(srcDir);
            File[] files = file.listFiles();
            System.out.println(":开始压缩图片...");
            long start = System.currentTimeMillis();
            for (File inFile : files){

                for(int q = 100 ; q>0; q-=5){
                    File outFile= new File(destDir+"\\"+getName(inFile,q));

                    FileInputStream inputStream = new FileInputStream(inFile);
                    GMUtil.saveImage(inputStream,outFile.getPath(),(double)q);
                }
            }
            System.out.println("压缩完成图片,耗时:"+(System.currentTimeMillis()-start));
    }

    private static String getName(File inFile,int q){
        String orgName = inFile.getName();
        String postfix = inFile.getName().substring(orgName.lastIndexOf('.'));
        String fileName = orgName.replace(postfix,"");
        return StringFormatter.format("%s_%s%s",fileName,q,postfix).getValue();
    }


    private static void testGrads(){

    }
    private static void getImageInfos() throws Exception {
        String srcDir= "E:\\技术中心\\jpg压缩\\图片测试\\原图";
        String destDir = "E:\\技术中心\\jpg压缩\\图片测试\\gm100";

        // for(int i =0 ;i<20;i++){
        File file = new File(srcDir);
        File[] files = file.listFiles();
        System.out.println(":开始压缩图片...");
        long start = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        for (File inFile : files){
            FileInputStream inputStream = new FileInputStream(inFile);
            ImageInfo imageInfo = GMUtil.getImageInfo(inputStream);
            sb.append(inFile.getName()+"|"+imageInfo.getQuality());
        }
        System.out.println("压缩完成图片,耗时:"+(System.currentTimeMillis()-start));
    }


    private static  void test() throws Exception {
        String srcDir= "E:\\技术中心\\jpg压缩\\图片测试\\原图";
        String destDir = "E:\\技术中心\\jpg压缩\\图片测试\\gm100";

        // for(int i =0 ;i<20;i++){
        File file = new File(srcDir);
        File[] files = file.listFiles();
        System.out.println(":开始压缩图片...");
        long start = System.currentTimeMillis();
        for (File inFile : files){



            File outFile= new File(destDir+"\\"+inFile.getName());
            FileInputStream inputStream = new FileInputStream(inFile);
            GMUtil.saveImage(inputStream,outFile.getPath(),100D);
        }
        System.out.println("压缩完成图片,耗时:"+(System.currentTimeMillis()-start));
    }



}
