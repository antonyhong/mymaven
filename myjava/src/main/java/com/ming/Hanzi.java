package com.ming;

/**
 * Created by hongyongming on 2016/7/11.
 */
public class Hanzi {
    public static void main(String[] args) {

       // String str = "helllo 你好!";
        //ongjiHanzi(str);

        toLowcase("AHEELO KEYZ");
        toLowcase("");
    }
    public static String tongjiHanzi(String str){
        if(str == null || str .length() ==0){
            return "hanzi:0";
        }
        char[] arr = str.toCharArray();
        int count=0;
        for(int i=0 ;i<arr.length;i++){
            if(arr[i] >=19968 &&arr[i]  <= 171941){
                count ++;
            }
        }
       return "hanzi:"+count;
    }


    public static String toLowcase(String str){
        if(str == null || str .length() ==0){
            return str;
        }
        char[] arr = str.toCharArray();
        for(int i=0 ;i<arr.length;i++){
            if(arr[i] >='A' &&arr[i]  <='Z'){
                arr[i] = (char)(arr[i] - ('A'-'a'));
            }
        }
        return String.copyValueOf(arr);
    }

}
