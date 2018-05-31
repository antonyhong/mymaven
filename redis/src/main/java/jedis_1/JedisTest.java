package jedis_1;

import com.alibaba.fastjson.JSON;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Map;

/**
 * Created by hongyongming on 2017/3/15.
 *
 *  Jedis 是非线程安全的.多并发的是后应该使用 JedisPool
 *
 */
public class JedisTest {
    public static void main(String[] args) {
        JedisPool jedisPool = new JedisPool("10.17.2.72", 6379);

        Jedis jedis = jedisPool.getResource();
        String studentKey = "myHashMapkey1";
        Map<String,String> map  = jedis.hgetAll(studentKey);
        jedis.hset(studentKey,"school","xxx Middle School");

        System.out.println(JSON.toJSONString(map));
        map  = jedis.hgetAll(studentKey);
        jedis.hset(studentKey,"school","xxx Middle School");

        System.out.println(JSON.toJSONString(map));
        //hashMapTest(jedisPool);

    }

    public static void hashMapTest(   JedisPool jedisPool ){
        Jedis jedis = jedisPool.getResource();
        String studentKey = "myHashMapkey1";

        jedis.del(studentKey);

        jedis.hset(studentKey,"name","李四");
        jedis.hset(studentKey,"age","15");
        jedis.hset(studentKey,"job","engineer");

        Map<String,String> map  = jedis.hgetAll(studentKey);

        System.out.println(JSON.toJSONString(map));


        jedis.hsetnx(studentKey,"age","16");
        jedis.hsetnx(studentKey, "class", "2");
        jedis.hset(studentKey,"grade","3");

        map  = jedis.hgetAll(studentKey);
        System.out.println(JSON.toJSONString(map));
        jedis.close();
    }


}
