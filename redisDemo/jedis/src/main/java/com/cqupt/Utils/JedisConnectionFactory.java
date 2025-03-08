package com.cqupt.Utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisConnectionFactory {
    private static final JedisPool jedispool;
//final变量必须在声明时或构造函数中初始化，否则编译器会报错。因为用户将jedispool声明为static final，所以它属于类级别的常量，必须在静态初始化块或声明时赋值，而不能在实例构造函数中初始化，否则会导致编译错误。
    static {
        //配置连接池
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(8);
        poolConfig.setMaxTotal(8);
        poolConfig.setMinIdle(0);
        poolConfig.setMaxWaitMillis(1000);

        //创建连接池对象
         jedispool = new JedisPool(poolConfig,"localhost",6379,1000);
    }

    public static Jedis getJedis(){
        return jedispool.getResource();
    }
}
