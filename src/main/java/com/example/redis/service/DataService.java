package com.example.redis.service;

import com.example.redis.dto.RedisDataLoadRequest;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import org.springframework.stereotype.Service;

@Service
public interface DataService {

    void saveData(RedisDataLoadRequest request);

    void saveDataWithExpireTime(RedisDataLoadRequest request, long timeout, TimeUnit unit);

    Object getData(String key);
}
