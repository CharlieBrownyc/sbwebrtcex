package com.example.mongodemo.service;

import com.example.mongodemo.domain.Person;
import com.example.mongodemo.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository repo;

    public Person create(Person p){
        return repo.save(p);
    }

    public Person get(String id){
        return repo.findById(id).orElseThrow();
    }

    public List<Person> getAll(){
        return repo.findAll();
    }

    public Person update(String id, String name, int age){
        Person p = get(id);
        p.setName(name);
        p.setAge(age);
        return repo.save(p);
    }

    public void delete(String id){
        repo.deleteById(id);
    }

    public Page<Person> getPage(int page, int size){
        return repo.findAll(PageRequest.of(page, size, Sort.by("name")));
    }

    public List<Person> searchByName(String keyword){
        return repo.findByNameContainingIgnoreCase(keyword);
    }

    public Page<Person> searchByName(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("name"));
        return repo.findByNameContainingIgnoreCase(keyword, pageable);
    }
}
