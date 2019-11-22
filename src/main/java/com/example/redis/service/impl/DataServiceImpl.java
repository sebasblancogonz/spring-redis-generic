package com.example.redis.service.impl;

import com.example.redis.config.RedisUtils;
import com.example.redis.dto.RedisDataLoadRequest;
import com.example.redis.service.DataService;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataServiceImpl implements DataService {

    private RedisUtils<Object> redisUtils;

    @Autowired
    public DataServiceImpl(RedisUtils<Object> redisUtils) {
        this.redisUtils = redisUtils;
    }

    @Override
    public void saveData(RedisDataLoadRequest request) {
        this.redisUtils.putValue(request.getKey(), request.getData());
    }

    @Override
    public void saveDataWithExpireTime(RedisDataLoadRequest request, long timeout, TimeUnit unit) {
        this.redisUtils.putValueWithExpireTime(request.getKey(), request.getData(), timeout, unit);
    }

    @Override
    public Object getData(String key) {
        return this.redisUtils.getValue(key);
    }
}
