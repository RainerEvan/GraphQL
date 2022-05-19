package com.example.demo.graphql;

import java.time.OffsetDateTime;
import java.util.UUID;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.example.demo.exception.AbstractGraphQLException;
import com.example.demo.model.Author;
import com.example.demo.model.Book;
import com.example.demo.model.BookId;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class MutationResolver implements GraphQLMutationResolver{
    
    @Autowired
    private final BookRepository bookRepository;
    @Autowired
    private final AuthorRepository authorRepository;

    public Book addBook(String name, UUID authorId){
        Book book = new Book();
        book.setName(name);
        Author author = authorRepository.findById(authorId).orElseThrow(() -> new AbstractGraphQLException("Author with current id not found"));
        book.setAuthor(author);
        book.setDateAdded(OffsetDateTime.now());
        return bookRepository.save(book);
    }

    public Book editBook(UUID id, String name){
        Book book = bookRepository.findById(id).orElseThrow(() -> new AbstractGraphQLException("Book with current id not found"));

        if(book!=null && name!=null){
            book.setName(name);
        }

        return bookRepository.save(book);
    }

    public Boolean deleteBook(UUID id){

        Book book = bookRepository.findById(id).orElseThrow(() -> new AbstractGraphQLException("Book with current id not found"));

        if(book==null){
            throw new AbstractGraphQLException("Book with current id not found");
        }

        bookRepository.deleteById(new BookId(book.getId(),book.getBookNo()));

        return true;
    }

    public Author addAuthor(String name){
        Author author = new Author();
        author.setName(name);
        return authorRepository.save(author);
    }
}
