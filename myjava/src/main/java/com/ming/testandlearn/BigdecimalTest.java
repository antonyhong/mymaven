package com.ming.testandlearn;

import java.math.BigDecimal;

/**
 * Created by hongyongming on 2016/10/13.
 */
public class BigdecimalTest {
    public static void main(String[] args) {
        double d = (34.9/0.28);
        //System.out.println((d)*0.28);
        System.out.println(d);
        BigDecimal m = new BigDecimal(String.valueOf((34.9 / 0.28)));
        System.out.println(m.doubleValue());
        BigDecimal b = new BigDecimal("0.28");
        BigDecimal result = m.divide(b);
        System.out.println(result.doubleValue());



    }
}
