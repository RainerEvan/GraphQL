package com.example.demo.repository;

import java.util.UUID;

import com.example.demo.model.Author;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author,UUID>{
    
}
