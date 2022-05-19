package com.example.demo.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.example.demo.exception.AbstractGraphQLException;
import com.example.demo.model.Author;
import com.example.demo.model.Book;
import com.example.demo.repository.AuthorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;


@Component
@AllArgsConstructor
public class BookResolver implements GraphQLResolver<Book>{

    @Autowired
    private final AuthorRepository authorRepository;

    public Author getAuthor(Book book){
        return authorRepository.findById(book.getAuthor().getId()).orElseThrow(() -> new AbstractGraphQLException("Author with current id not found"));
    }
}
