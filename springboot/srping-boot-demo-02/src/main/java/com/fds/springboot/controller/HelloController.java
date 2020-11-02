package com.fds.springboot.controller;

import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private RedissonClient redissonClient;

    @GetMapping("/hello")
    private String hello(){
        RBucket bucket = redissonClient.getBucket("name");
        if ( bucket.get() == null) {
            bucket.set("com.fds");
        }
        return bucket.get().toString();
    }
}
