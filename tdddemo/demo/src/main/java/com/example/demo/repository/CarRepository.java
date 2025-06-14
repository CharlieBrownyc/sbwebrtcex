package com.example.demo.repository;

import com.example.demo.entity.Car;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends Repository<Car, Long> {
    public Optional<Car> findByName(String name);

//    List<Car> findByName(String name);
}
