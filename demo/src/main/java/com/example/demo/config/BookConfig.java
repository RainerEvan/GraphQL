package com.example.demo.config;

import com.example.demo.model.Author;
import com.example.demo.model.Book;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BookRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BookConfig {
    @Bean
        CommandLineRunner commandLineRunner(BookRepository bookRepository, AuthorRepository authorRepository){
            return args -> {

                Author author = new Author();
                author.setName("Rainer");
                authorRepository.save(author);

                Book book = new Book();
                book.setName("Harry Potter and The Black Swan");
                book.setAuthor(author);
                bookRepository.save(book);
            };
        }
}
