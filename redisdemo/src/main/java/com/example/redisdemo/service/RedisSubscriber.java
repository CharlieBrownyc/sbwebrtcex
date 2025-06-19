package com.example.redisdemo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisSubscriber {

    private final RedisPublisher publisher;

    public void receiveMessage(String msg) {
        System.out.println("Received via Redis Pub/Sub: " + msg);

        String broadcastMsg = "[broadcast] ";
        if(!msg.startsWith(broadcastMsg)){
            publisher.publish("chat", broadcastMsg + msg);
        }
    }
}
