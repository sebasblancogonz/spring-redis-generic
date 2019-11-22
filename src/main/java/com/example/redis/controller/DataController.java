package com.example.redis.controller;

import com.example.redis.dto.RedisDataLoadRequest;
import com.example.redis.service.DataService;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/redisDataLoader")
public class DataController {

    private final Logger LOGGER = Logger.getLogger("com.example.redis.controller.DataController");

    @Autowired
    private DataService dataService;

    @GetMapping(value = "/healthcheck", produces = "application/json;charset=utf-8")
    public String getHealthCheck() {
        return "{ \"isWorking\" : true }";
    }

    @GetMapping(value = "/{key}", produces = "application/json")
    public Object save(@PathVariable String key) {
        try {
            return this.dataService.getData(key);
        } catch (Exception e) {
            LOGGER.info(e.toString());
        }
        return "saved";
    }

    @PostMapping(value = "/", produces = "application/json")
    public String saveData(@RequestBody RedisDataLoadRequest request) {
        try {
            this.dataService.saveData(request);
        } catch (Exception e) {
            LOGGER.info(e.toString());
        }
        return "saved";
    }
}
