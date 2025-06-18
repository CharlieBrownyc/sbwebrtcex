package com.example.redisdemo.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisPublisher {

    private final RedisTemplate<String, String> template;
    public RedisPublisher(RedisTemplate<String, String> template) {
        this.template = template;
    }

    public void publish(String topic, String msg) {
        template.convertAndSend(topic, msg);
    }
}
