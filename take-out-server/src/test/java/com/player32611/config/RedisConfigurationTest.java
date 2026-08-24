package com.player32611.config;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@SpringBootTest
@Slf4j
public class RedisConfigurationTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testRedisTemplate() {
        log.info("Redis 单元测试: {}", redisTemplate);
    }

    @Test
    public void testString(){
        ValueOperations valueOperations = redisTemplate.opsForValue();

        valueOperations.set("city", "北京");
        log.info("SET key value: city, 北京");

        String city = (String)valueOperations.get("city");
        log.info("GET key: {}", city);

        valueOperations.set("code", "1234",3, TimeUnit.MINUTES);
        log.info("SETEX key seconds value: code, 1234, 3, TimeUnit.MINUTES");

        valueOperations.setIfAbsent("lock", "1");
        log.info("SETNX key value: lock, 1");
        String lock = (String)valueOperations.get("lock");
        log.info("GET key: {}", lock);

        valueOperations.setIfAbsent("lock", "2");
        log.info("SETNX key value: lock, 2");
        lock = (String)valueOperations.get("lock");
        log.info("GET key: {}", lock);
    }

    @Test
    public void testHash(){
        HashOperations hashOperations = redisTemplate.opsForHash();

        hashOperations.put("100", "name", "tom");
        log.info("HSET key field value: 100, name, tom");
        hashOperations.put("100", "age", "20");
        log.info("HSET key field value: 100, age, 20");

        String name = (String) hashOperations.get("100","name");
        log.info("HGET key field: {}", name);

        Set<String> keys = hashOperations.keys("100");
        log.info("HKEYS key field: {}", keys);

        List<String> values = hashOperations.values("100");
        log.info("HVALS key: {}", values);

        hashOperations.delete("100", "age");
        log.info("HDEL key field: 100, age");
    }
}
