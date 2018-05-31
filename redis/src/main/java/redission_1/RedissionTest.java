package redission_1;


import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.codec.SmileJacksonCodec;
import org.redisson.config.Config;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;

/**
 * Created by hongyongming on 2017/3/15.
 */
public class RedissionTest {
    public static void main(String[] args) {


        //创建配置信息
        Config config = new Config();
        config.setCodec(new StringCodec()).useSingleServer().setAddress("10.17.2.72:6379").setConnectionPoolSize(10);

        RedissonClient redisson = Redisson.create(config);

        hashMapTest(redisson);
    }
    public static void hashMapTest(   RedissonClient redisson ){
        String studentKey = "myHashMapkey1";


        Map<String,Object> map = redisson.getMap(studentKey);

//        map.clear();
//        System.out.println(map.size());
//        //不能直接序列化
//        //System.out.println(JSON.toJSONString(map));
//        map.put("school","xxx primary school");
//        map.put("age",1);
//        map.put("name","张三");
//        map.put("class",1);
//
//        String name =(String) map.get("name");
//        System.out.println("name:"+name);
//        System.out.println("");


        System.out.println("read from redis:");
        Map<String,Object> map2 = redisson.getMap(studentKey);
        RLock lock = redisson.getLock(studentKey);
        lock.lock();
        map2.entrySet().forEach(e-> System.out.println(e.getKey()+":"+e.getValue()));

        lock.unlock();;

        //redisson.createBatch()
    }

}

