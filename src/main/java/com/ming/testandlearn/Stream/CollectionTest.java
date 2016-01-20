package com.ming.testandlearn.Stream;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by hongyongming on 2015/11/4.
 */
public class CollectionTest {

    public static void main(String[] args) throws Exception {
        testFilter();
        testFilter_1();
        testFilter_2();
        testFilter_3();
    }


    public static void testFilter() throws Exception {
        List<String> list = Arrays.asList("wer", "tt", "aa", "taa");
        List<String> reList = filterAndTransfer(list,str -> str.startsWith("t"),str ->str);
        reList.forEach(str->System.out.print(str+"\t"));
        System.out.println("new:\n");
    }
    public static void testFilter_1() throws Exception {
        List<String> list = Arrays.asList("wer","saft","aa","asdftasdfaa");
        List<String> reList = filterAndTransfer(list, str -> str.startsWith("t"), str -> str);
        reList.forEach(str->System.out.print(str+"\t"));
        System.out.println("new:\n");
    }

    public static void testFilter_2() throws Exception {
        List<String> list = Arrays.asList();
        List<String> reList = filterAndTransfer(list, str -> str.startsWith("t"), str -> str);
        reList.forEach(str->System.out.print(str+"\t"));
        System.out.println("new:\n");
    }

    public static void testFilter_3() throws Exception {
        List<String> list = null;
        List<String> reList = filterAndTransfer(list, str -> str.startsWith("t"), str -> str);
        reList.forEach(str->System.out.print(str+"\t"));
        System.out.println("new:\n");
    }

    private static <T,R> List<R> filterAndTransfer(List<T> originList, Predicate<? super T> filterPredicate, Function<? super T, ? extends R> transferMapper){
        List<R> result = originList.stream().filter(filterPredicate).map(transferMapper).collect(Collectors.toList());
        return result;
    }
}
