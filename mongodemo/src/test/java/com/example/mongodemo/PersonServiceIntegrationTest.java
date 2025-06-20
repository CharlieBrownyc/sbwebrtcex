package com.example.mongodemo;

import com.example.mongodemo.domain.Person;
import com.example.mongodemo.repository.PersonRepository;
import com.example.mongodemo.service.PersonService;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.DynamicPropertyRegistry;

import java.util.List;

@DataMongoTest
class PersonServiceIntegrationTest {

    @Autowired
    PersonRepository repo;

    @Test
    void bulkInsert_and_list() {
        for(int i=1; i<=100; i++){
            repo.save(new Person(null, "User" + i, i));
        }
        Assertions.assertEquals(100, repo.findAll().size());
    }
}
