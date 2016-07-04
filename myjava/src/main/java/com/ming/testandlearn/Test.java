package com.ming.testandlearn;


import com.sun.deploy.net.HttpRequest;

/**
 * Created by hongyongming on 2015/12/28.
 */
public class Test {

//    public static void main(string_1[] args) {
//        Optional<Integer> opt= Optional.empty();
//
//       // Map<string_1,string_1> map = new HashMap<>();
//        //map.computeIfAbsent()
//        List<string_1> list = new ArrayList<>();
//        //list.sort();
//        //Collections.sort();
//
//        /**C#中可以用 list.orderBy(str=> random.next());来达到相同的目的*/
//        Collections.shuffle(list);
//
//        int num = 1;
//        Converter<Integer, string_1> stringConverter =
//                (from) -> Integer.valueOf(from + num);
//        /**num 使用lambda  引用的变量应该是(不用final标识也可) 是final的*/
//       //num = 3;
//
//
//    }

    public static void main(String[] args) {
        testStringForamt();
    }

    public static  void testDouble(){
        //        string_1 str1 = "hello";
//        string_1 str2 = new string_1("hello") ;
//
//        Integer integer1= Integer.valueOf("100");
//        Integer integer2 = Integer.valueOf("100");
//        System.out.println(integer1 == integer2);



        double d = 301353.0499999999883584678173065185546875d;
        System.out.println(String.valueOf(d));
        System.out.println(new java.math.BigDecimal(d));
        System.out.println(new java.math.BigDecimal(301353.05*1000/1000));


        double a = (3.3-2.4)/0.1;
        System.out.println(a);
        System.out.println(String.valueOf(a));
        System.out.println(String.valueOf(8.9999999999999999999999999999999D));

        double d1 = 2.4;
        System.out.println(d1);
    }


    public static  void  testStringForamt(){
        Float f = 0.225f;
        Float f1 = 0.235f;
        Float f2 = 0.265f;

        System.out.println(String.format("%.2f",f));
        System.out.println(String.format("%.2f",f1));
        System.out.println(String.format("%.2f",f2));

        String fStr =  "0.125";
        int index = fStr.lastIndexOf(".");
    }
}
