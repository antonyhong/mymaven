package compress;

import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.metadata.exif.ExifImageDirectory;
import com.sun.imageio.plugins.jpeg.JPEGImageWriter;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.ImageWriter;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

/**
 * Created by HONGYONGMING on 2017/3/2.
 */
public class ImageInfo {
    public static void main(String[] args) throws JpegProcessingException, IOException {
        String srcDir = "E:\\技术中心\\jpg压缩\\图片测试\\java75";
        String destDir = "E:\\技术中心\\jpg压缩\\图片测试\\java75x2";
        File file = new File(srcDir);
        File[] files = file.listFiles();
        System.out.println("读取图片信息...");
        long start = System.currentTimeMillis();
        for (File inFile : files) {

            if(inFile.getName().endsWith("png")){continue;}
            Metadata metadata = JpegMetadataReader.readMetadata(inFile);


            Directory exif = metadata.getFirstDirectoryOfType(ExifImageDirectory.class);
            if (exif == null) {
                continue;
            }
            Iterator tags = exif.getTags().iterator();
            while (tags.hasNext()) {
                Tag tag = (Tag) tags.next();
                System.out.println(tag);
            }

        }
        System.out.println("压缩完成图片,耗时:" + (System.currentTimeMillis() - start));
    }
}
