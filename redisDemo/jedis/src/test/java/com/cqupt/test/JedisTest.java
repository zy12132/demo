package com.cqupt.test;

import com.cqupt.Utils.JedisConnectionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

import java.util.Map;

public class JedisTest {
    private Jedis jedis;

    @BeforeEach
    void setUp(){
        //1.建立连接
//        jedis = new Jedis("localhost",6379);
        jedis = JedisConnectionFactory.getJedis();
        //2.设置密码
//        jedis.auth("123");
        //3.选择库
        jedis.select(0);
    }

    @Test
    void testString(){

        // 设置和获取键值
        String result = jedis.set("name", "lisa");
        System.out.println("result = "+ result);
        //获取数据
        String name = jedis.get("name");
        System.out.println("name = "+ name);
    }

    @Test
    void testHash(){
        // 设置和获取键值
        jedis.hset("user:1","name","Jack");
        jedis.hset("user:1","age","21");

        //获取
        Map<String, String> map = jedis.hgetAll("user:1");
        System.out.println(map);
    }
    @AfterEach
    void tearDown(){
        //关闭连接
        if (jedis != null){
            jedis.close();
        }
    }
}
