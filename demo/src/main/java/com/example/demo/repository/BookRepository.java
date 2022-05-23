package com.example.demo.repository;


import java.util.Optional;
import java.util.UUID;

import com.example.demo.model.Book;
import com.example.demo.model.BookId;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,BookId>{
    Optional<Book> findById(UUID id);
    Optional<Book> findByBookNo(String bookNo);
}
