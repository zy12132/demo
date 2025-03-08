package com.cqupt.redisdatademo;

import com.cqupt.redisdatademo.Entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class RedisDataDemoApplicationTests {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private static final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    void testStringOne() {


        //写入String数据
        stringRedisTemplate.opsForValue().set("name","约翰");

        //获取String数据
        Object name = stringRedisTemplate.opsForValue().get("name");

        logger.info("姓名为 {}", name);

    }


    @Test
    void testString() throws JsonProcessingException {

       //创建对象
        User user = new User("giao哥", 64);

        //手动序列化
        String json = mapper.writeValueAsString(user);

        stringRedisTemplate.opsForValue().set("user:01",json);

        String jsonUser = stringRedisTemplate.opsForValue().get("user:01");

        logger.info("信息{}", jsonUser);
    }

}
