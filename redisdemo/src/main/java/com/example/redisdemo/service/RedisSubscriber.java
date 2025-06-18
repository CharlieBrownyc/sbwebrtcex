package com.example.redisdemo.service;

import org.springframework.stereotype.Service;

@Service
public class RedisSubscriber {

    public void receiveMessage(String msg) {
        System.out.println("Received via Redis Pub/Sub: " + msg);
    }
}
