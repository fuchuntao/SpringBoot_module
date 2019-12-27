package com.javen.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class RedisService {
    @Autowired
    private RedisTemplate redisTemplate;

    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }


    public boolean set(final String key, Object value) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            log.error("set error: key {}, value {}", key, value, e);
        }
        return result;
    }


    public boolean set(final String key, Object value, Long expireTime) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            log.error("set error: key {}, value {},expireTime {}", key, value, expireTime, e);
        }
        return result;
    }

    public Object get(final String key) {
        try {
            if (exists(key)) {
                ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
                Object obj = operations.get(key);
                if (obj != null)
                    return obj;
            }
        } catch (Exception e) {
            log.error("get error: key {}", key, e);
        }
        return null;
    }

    public void remove(final String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }

    public void remove(final String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }

    public long removePattern(final String pattern) {
        if (exists(pattern)) {
            Set<Serializable> keys = redisTemplate.keys(pattern);
            if (keys.size() > 0) {
                return redisTemplate.delete(keys);
            }
        }
        return 0;
    }

    public void hashSet(String key, Object hashKey, Object value) {
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        hash.put(key, hashKey, value);
    }

    public Object hashGet(String key, Object hashKey) {
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        return hash.get(key, hashKey);
    }

    public void push(String k, Object v) {
        ListOperations<String, Object> list = redisTemplate.opsForList();
        list.rightPush(k, v);
    }

    public List<Object> range(String k, long l1, long l2) {
        ListOperations<String, Object> list = redisTemplate.opsForList();
        return list.range(k, l1, l2);
    }

    public void setAdd(String key, Object value) {
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        set.add(key, value);
    }

    public Set<Object> setMembers(String key) {
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        return set.members(key);
    }

    public void zAdd(String key, Object value, double scoure) {
        ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
        zset.add(key, value, scoure);
    }

    public Set<Object> rangeByScore(String key, double scoure1, double scoure2) {
        ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
        return zset.rangeByScore(key, scoure1, scoure2);
    }
}