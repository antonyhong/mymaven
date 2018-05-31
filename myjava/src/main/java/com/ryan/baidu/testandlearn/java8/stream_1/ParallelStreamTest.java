package com.ryan.baidu.testandlearn.java8.stream_1;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import static java.util.stream.Collectors.*;
/**
 * Created by hongyongming on 2016/1/4.
 */
public class ParallelStreamTest {
    private static ThreadLocalRandom random = ThreadLocalRandom.current();
    public static void main(String[] args) {
        //todo
      Map<Integer,Double> map =   IntStream.range(0, 10000)
                .parallel()
                .mapToObj(i->twoDiceThrows())
                .collect(Collectors.groupingBy(side->side,summingDouble(n->n)));
        //map.forEach(kv-> System.out.println(kv.doubleValue()));
        map.forEach((k,v)-> System.out.println(String.format("K:%s;V:%s",k,v)));
    }

    private static int twoDiceThrows(){

        ThreadLocalRandom random = ThreadLocalRandom.current();
        int first = random.nextInt();
        int second = random.nextInt();
        return first + second;
    }




}
