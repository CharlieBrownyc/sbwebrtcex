package com.example.redisdemo.service;

import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PersonServiceTest {

    @Autowired
    MockMvc mvc;

    @Test
    void cachingWorks() throws Exception{

        long start = System.currentTimeMillis();
        mvc.perform(get("/person/42")).andExpect(status().isOk());
        long first = System.currentTimeMillis() - start;
        Assertions.assertTrue(first >= 2000);

        start = System.currentTimeMillis();
        mvc.perform(get("/person/42")).andExpect(status().isOk());
        long second = System.currentTimeMillis() - start;
        Assertions.assertTrue(second < 200);
    }
}
