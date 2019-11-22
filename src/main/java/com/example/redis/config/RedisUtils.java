package com.example.redis.config;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@Configuration
public class RedisUtils<T> {

    private RedisTemplate<String, T> redisTemplate;
    private HashOperations<String, Object, T> hashOperations;
    private ValueOperations<String, T> valueOperations;

    @Autowired
    public RedisUtils(RedisTemplate<String, T> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.hashOperations = redisTemplate.opsForHash();
        this.valueOperations = redisTemplate.opsForValue();
    }

    public Map<Object, T> getMapsAsAll(String redisKey) {
        return this.hashOperations.entries(redisKey);
    }

    public void putValue(String key, T value) {
        this.valueOperations.set(key, value);
    }

    public void putValueWithExpireTime(String key, T value, long timeout, TimeUnit unit) {
        this.valueOperations.set(key, value, timeout, unit);
    }

    public T getValue(String key) {
        return this.valueOperations.get(key);
    }
}
