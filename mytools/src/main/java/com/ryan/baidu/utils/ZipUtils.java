package com.ryan.baidu.utils;


import java.io.*;
import java.nio.charset.Charset;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Created by hongyongming on 2015/9/22.
 */
public class ZipUtils {

    public static void zipFile(File inFile, ZipOutputStream zos, String dir) throws IOException {
        if (inFile.isDirectory()) {
            File[] files = inFile.listFiles();
            for (File file : files) {
                zipFile(file, zos, dir + "\\" + inFile.getName());
            }
        } else {
            String entryName = null;
            if (!"".equals(dir))
                entryName = dir + "\\" + inFile.getName();
            else
                entryName = inFile.getName();
            ZipEntry entry = new ZipEntry(entryName);
            zos.putNextEntry(entry);
            InputStream is = new FileInputStream(inFile);
            int len = 0;
            while ((len = is.read()) != -1)
                zos.write(len);
            is.close();
        }
    }
    public static void unZip(String file,String destDir) throws IOException {
        // File file = new File("D:\\testandlearn.zip");//压缩文件
        ZipFile zipFile = new ZipFile(file);//实例化ZipFile，每一个zip压缩文件都可以表示为一个ZipFile
        //实例化一个Zip压缩文件的ZipInputStream对象，可以利用该类的getNextEntry()方法依次拿到每一个ZipEntry对象
        ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(file), Charset.forName("GBK"));
        ZipEntry zipEntry = null;
        while ((zipEntry = zipInputStream.getNextEntry()) != null) {
            String fileName = zipEntry.getName();
            File temp = new File(destDir+ "/" + fileName);
            if (! temp.getParentFile().exists())
                temp.getParentFile().mkdirs();
            OutputStream os = new FileOutputStream(temp);
            //通过ZipFile的getInputStream方法拿到具体的ZipEntry的输入流
            InputStream is = zipFile.getInputStream(zipEntry);
            int len = 0;
            while ((len = is.read()) != -1)
                os.write(len);
            os.close();
            is.close();
        }
        zipInputStream.close();
    }


    public static void main(String args[]) throws IOException {
        test1();
        test2();
    }

    public static void test1() throws IOException {
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream("D:\\testZip.zip"), Charset.forName("GBK"));
        //实例化一个名称为ab.txt的ZipEntry对象
        ZipEntry entry = new ZipEntry("ab.txt");
        //设置注释
        zos.setComment("zip测试for单个文件");
        //把生成的ZipEntry对象加入到压缩文件中，而之后往压缩文件中写入的内容都会放在这个ZipEntry对象里面
        zos.putNextEntry(entry);
        InputStream is = new FileInputStream("D:\\ab.txt");
        int len = 0;
        while ((len = is.read()) != -1)
            zos.write(len);
        is.close();
        zos.close();
    }

    public static void test2() throws IOException {
        File inFile = new File("D:\\testandlearn");
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream("D:\\testandlearn.zip"), Charset.forName("GBK"));
        zos.setComment("多文件处理");
        zipFile(inFile, zos, "");
        zos.close();
    }






}
