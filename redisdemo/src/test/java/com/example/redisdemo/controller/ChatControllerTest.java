package com.example.redisdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ChatControllerTest {

    @Autowired
    MockMvc mvc;

    @Test
    void chatSendShowsMessage() throws Exception {
        mvc.perform(post("/send")
                        .param("message", "hello"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("msg", "hello"))
                .andExpect(view().name("chat"));
    }
}
