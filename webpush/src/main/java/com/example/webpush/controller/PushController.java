package com.example.webpush.controller;

import com.example.webpush.model.Subscription;

import com.example.webpush.service.WebPushService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/push")
@RequiredArgsConstructor
class PushController {

    private final WebPushService service;


    @GetMapping("/key")
    public String getKey() {
        return service.getPublicKey();
    }

    @PostMapping("/subscribe")
    public void subscribe(@RequestBody Subscription sub) {
        service.subscribe(sub);
    }

    @PostMapping("/send")
    public void send(@RequestBody Map<String, String> body) {
        service.send(body.get("message"));
    }
}
