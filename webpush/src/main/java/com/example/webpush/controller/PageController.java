package com.example.webpush.controller;

import com.example.webpush.service.WebPushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @Autowired
    WebPushService webPushService;

    @GetMapping("/")
    public String index(Model model){
        String apiKey = webPushService.getPublicKey();
        System.out.println("PageController::apiKey:" + apiKey);
        model.addAttribute("apiKey", apiKey);
        return "index";
    }
}
