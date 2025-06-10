package com.example.webrtcexam.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.webrtcexam.dto.SignalMessage;

@Controller
public class SignalController {
    
    @MessageMapping("/signal")
    @SendTo("/topic/signals")
    public SignalMessage relay(SignalMessage msg) {
        // Here you can process the incoming signal message if needed
        // For now, we just return it to be broadcasted to all subscribers
        if("chat".equals(msg.getType())) {
            // Handle chat messages differently if needed
            System.out.println("Chat message received: " + msg.getSdp());
            System.out.println("채팅 수신: " + msg.getSender() + ": " + msg.getContent());
        } else {
            System.out.println("Signal message received: " + msg);
        }
        return msg;
    }

    @GetMapping("/")
    public String index() {
        // Return the name of the HTML file to be served
        return "index"; // This assumes you have an index.html in src/main/resources/templates
    }
}
