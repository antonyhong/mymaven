package com.ming.testandlearn.io_test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Files;

/**
 * Created by hongyongming on 2016/7/8.
 */
public class IOTest {

    public static void main(String[] args) {

        //System.out.println(args[2]);
        testByte();

    }

    public static  void  test(int i){
        System.out.println(i);
    }

    public static void testByte(){
        byte b = 64;
        byte c =  -6;
        System.out.println(b/c +" "+ b%c);

    }
    public static  void  testReadFile(String fileName) throws FileNotFoundException {
        //File file = new File(fileName);
      //  FileInputStream fin = new FileInputStream(file);
        //FileReader reader = new FileReader();
        //short[] ye[][];
        Double a =new Double("-5.55");
        Double b=new Double("-5.55");
        System.out.println(a==b);
        System.out.println(a.equals(b));
    }



}
