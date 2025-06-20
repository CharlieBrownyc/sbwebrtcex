package com.example.mongodemo.repository;

import com.example.mongodemo.domain.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface PersonRepository extends MongoRepository<Person, String> {
    List<Person> findByNameContainingIgnoreCase(String keyword);
    Page<Person> findByNameContainingIgnoreCase(String keyword, Pageable pageable);
}
