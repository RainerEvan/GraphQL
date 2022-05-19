package com.example.demo.graphql;

import java.util.List;
import java.util.UUID;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.demo.exception.AbstractGraphQLException;
import com.example.demo.model.Author;
import com.example.demo.model.Book;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class QueryResolver implements GraphQLQueryResolver{
    
    @Autowired
    private final BookRepository bookRepository;
    @Autowired
    private final AuthorRepository authorRepository;

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public List<Author> getAllAuthors(){
        return authorRepository.findAll();
    }

    public Book getBook(String id){

        if(id.length()<36){
            return bookRepository.findByBookNo(id).orElse(null);
        }
        else{
            return bookRepository.findById(UUID.fromString(id)).orElseThrow(()->new AbstractGraphQLException("Book with current id not found"));
        }

    }
}
