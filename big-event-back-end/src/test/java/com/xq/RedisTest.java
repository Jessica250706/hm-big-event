package com.xq;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;

@SpringBootTest // 如果在测试类上添加了这个注解，那么将来在单元测试方式执行之前，会先初始化 Spring 容器
public class RedisTest {

    @Autowired
    private StringRedisTemplate template;

    @Test
    public void testSet() {
        // 往 redis 中存储一个键值对 SpringRedisTemplate
        ValueOperations<String, String> operations = template.opsForValue();

        operations.set("username", "zhangsan");
        operations.set("id", "001", 15, TimeUnit.SECONDS);
    }

    @Test
    public void testGet() {
        // 从 redis 中获取一个键值对
        ValueOperations<String, String> operations = template.opsForValue();
        System.out.println(operations.get("username"));
    }
}
