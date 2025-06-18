package com.example.redisdemo.controller;

import com.example.redisdemo.service.RedisPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final RedisPublisher publisher;

    @GetMapping("/")
    public String chatPage() {
        return "chat"; // src/main/resources/templates/chat.html
    }

    @PostMapping("/send")
    public String sendMessage(@RequestParam String message, Model model) {
        publisher.publish("chat", message);
        model.addAttribute("msg", message);
        return "chat";
    }
}
