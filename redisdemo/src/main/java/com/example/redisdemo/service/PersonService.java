package com.example.redisdemo.service;

import com.example.redisdemo.domain.Person;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Cacheable(value = "persons", key = "#id")
    public Person getPerson(String id){
        simulateSlowService();
        return new Person(id, "Person#" + id);
    }

    private void simulateSlowService() {
        try{
            Thread.sleep(2000);
        }catch (Exception e){

        }
    }
}
