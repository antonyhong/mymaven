package com.ming.testandlearn.string_1;

/**
 * Created by hongyongming on 2016/5/13.
 */
public class Strings {

    public static int getCount(String content, String word) {
        char[] source = content.toCharArray();
        char[] target = word.toCharArray();
        //content.contains(word);
        int count = 0;
        int targetIndex = 0;
        final int targetEndIndex = target.length - 1;
        for (int i = 0; i <= source.length - target.length; i++) {
            if (source[i] != target[targetIndex]) {
                targetIndex = 0;
                continue;
            }
            if (targetIndex == targetEndIndex) {
                count++;
                targetIndex = 0;
            }else{
                targetIndex++;
            }

        }
        return count;
    }




    public static void main(String[] args) {
        String source = "我是中国人,我_是小学生,我是成人,我是地球人";
        System.out.println("我是:"+getCount(source,"我是"));
        System.out.println("人:"+getCount(source,"人"));
        System.out.println("是:"+getCount(source,"是"));
        System.out.println("不是:"+getCount(source,"不是"));
        System.out.println("我:"+getCount(source,"我"));
    }
}
