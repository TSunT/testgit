package cn.edu.nuaa.redis.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.util.Properties;

public class JedisUtil {

    private static JedisPool jedisPool;
    static {
        Properties properties = new Properties();
        try {
            properties.load(JedisUtil.class.getClassLoader().getResourceAsStream("jedis.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(Integer.parseInt(properties.getProperty("MaxTotal")));
        config.setMaxIdle(Integer.parseInt(properties.getProperty("MaxIdle")));

        jedisPool= new JedisPool(config,properties.getProperty("host"),Integer.parseInt(properties.getProperty("port")));
    }
    public static Jedis getJedis(){
        return jedisPool.getResource();
    }
}
