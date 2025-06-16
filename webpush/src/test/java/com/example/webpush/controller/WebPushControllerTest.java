package com.example.webpush.controller;

import com.example.webpush.model.Subscription;
import com.example.webpush.service.WebPushService;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.junit.jupiter.api.Test;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;


import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WebPushControllerTest.class)
@Import(WebPushService.class)
public class WebPushControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    WebPushService webPushService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void publicKeyEndpoint_returnsKey() throws Exception {
        System.out.println("getPublicKey:" + webPushService.getPublicKey());
        Mockito.when(webPushService.getPublicKey()).thenReturn("PUBKEY");
        mockMvc.perform(get("/api/push/key"))
                .andExpect(status().isOk())
                .andExpect(content().string("PUBKEY"));
    }

    @Test
    void subscribeEndpoint_callsService() throws Exception {
        Subscription sub = new Subscription("https://ex", Map.of("p256dh", "KEY", "auth", "AUTH"));
        mockMvc.perform(post("/api/push/subscribe")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sub)))
                .andExpect(status().isOk());

        Mockito.verify(webPushService).subscribe(sub);
    }

    @Test
    void sendEndpoint_callsService() throws Exception {
        Map<String,String> body = Map.of("message","Hello");
        mockMvc.perform(post("/api/push/send")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(body)))
                .andExpect(status().isOk());

        Mockito.verify(webPushService).send("Hello");
    }
}
