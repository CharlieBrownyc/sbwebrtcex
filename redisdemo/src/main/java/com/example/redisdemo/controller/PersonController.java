package com.example.redisdemo.controller;

import com.example.redisdemo.domain.Person;
import com.example.redisdemo.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService service;

    @GetMapping("/{id}")
    public Person get(@PathVariable String id){
        return service.getPerson(id);
    }

}
