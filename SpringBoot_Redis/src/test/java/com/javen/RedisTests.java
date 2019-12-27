package com.javen;

import com.javen.redis.RedisService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisService redisService;

    @Test
    public void testRedisService() throws Exception {
        redisService.set("pay", "IJPay");
        Assert.assertEquals("IJPay", redisService.get("pay"));
    }

    @Test
    public void testRedisService2() throws Exception {
        Assert.assertEquals(1,redisService.removePattern("pay"));
    }

    @Test
    public void testString() {
        redisTemplate.opsForValue().set("testRedis", "test Redis By Javen");
        Assert.assertEquals("test Redis By Javen", redisTemplate.opsForValue().get("testRedis"));
    }

}
