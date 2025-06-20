package com.example.mongodemo.controller;

import com.example.mongodemo.domain.Person;
import com.example.mongodemo.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persons")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService service;

    @PostMapping
    public ResponseEntity<Person> create(@RequestBody Person p) {
        return ResponseEntity.ok(service.create(p));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> get(@PathVariable String id) {
        return ResponseEntity.ok(service.get(id));
    }

    @GetMapping
    public ResponseEntity<List<Person>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }
}
