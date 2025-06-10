package com.example.webrtcexam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.webrtcexam.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    // This interface will automatically provide CRUD operations for User entities.
    // You can add custom query methods if needed.
    
}
