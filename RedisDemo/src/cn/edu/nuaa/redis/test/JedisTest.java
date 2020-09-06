package cn.edu.nuaa.redis.test;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Map;
import java.util.Set;

public class JedisTest {
    /**
     * 快速入门String操作
     */
    @Test
    public void test1(){
        //1.获取连接
        Jedis jedis = new Jedis("localhost", 6379);
        //2.操作
        jedis.set("username2","jack");
        String name = jedis.get("username2");
        System.out.println(name);
        //3.关闭连接
        jedis.close();
    }
    /*
        hash操作
     */
    @Test
    public void test2(){
        //1.获取连接
        Jedis jedis = new Jedis("localhost", 6379);
        //2.操作
        jedis.hset("myhash","username","tim");
        jedis.hset("myhash","age","23");
        jedis.hset("myhash","gender","male");

        System.out.println(jedis.hget("myhash","username"));

        Map<String, String> myhash = jedis.hgetAll("myhash");
        Set<Map.Entry<String, String>> entries = myhash.entrySet();
        for (Map.Entry<String,String> entry :entries) {
            System.out.println(entry.getKey()+":"+entry.getValue());
        }
        //3.关闭连接
        jedis.close();
    }

    @Test
    public void testpool(){
        JedisPool jedisPool = new JedisPool("localhost", 6379);
        Jedis resource = jedisPool.getResource();
        resource.set("hehe","hahah");
        System.out.println(resource.get("hehe"));
        resource.close();
    }
}
