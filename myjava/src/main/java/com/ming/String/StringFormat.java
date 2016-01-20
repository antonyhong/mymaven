package com.ming.String;

/**
 * Created by hongyongming on 2015/12/23.
 */
public class StringFormat {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        String str1 = "欢迎";
        String str2 = "广州";
        for(int i=0;i<10;i++){
            //System.out.println(String.format("%s%s%s",str1,i,str2));
        }
        //System.out.println("string format耗时:"+ (System.currentTimeMillis()-start));


        start = System.currentTimeMillis();
        for(int i=0;i<10;i++){
            System.out.println(str1+i+str2);
        }
        System.out.println("string +耗时:"+ (System.currentTimeMillis()-start));
    }
}
