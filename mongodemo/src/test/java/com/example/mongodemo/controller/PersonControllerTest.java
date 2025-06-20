package com.example.mongodemo.controller;

import com.example.mongodemo.domain.Person;
import com.example.mongodemo.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class PersonControllerTest {

    @Autowired
    MockMvc mvc;

    @MockitoBean
    PersonRepository repo;

    @Test
    void createAndGetPerson() throws Exception {
        Person p = new Person("1", "Alice", 25);
        when(repo.save(any())).thenReturn(p);
        when(repo.findById("1")).thenReturn(java.util.Optional.of(p));

        mvc.perform(post("/persons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Alice\",\"age\":25}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"));

        mvc.perform(get("/persons/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Alice"));
    }
}
