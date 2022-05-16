package com.example.demo.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.example.demo.exception.BookNotFoundException;
import com.example.demo.model.Author;
import com.example.demo.model.Book;
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

    public Book addBook(String name, Long authorId){
        Book book = new Book();
        book.setName(name);
        Author author = authorRepository.findById(authorId).orElseThrow(() -> new IllegalStateException("Author not found"));
        book.setAuthor(author);
        return bookRepository.save(book);
    }

    public Book editBook(Long id, String name){
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book with current id is not found: ", id));

        if(book!=null && name!=null){
            book.setName(name);
        }

        return bookRepository.save(book);
    }

    public Boolean deleteBook(Long id){
        boolean exist = bookRepository.existsById(id);

        if(!exist){
            throw new BookNotFoundException("Book with current id is not found: ", id);
        }

        bookRepository.deleteById(id);

        return true;
    }

    public Author addAuthor(String name){
        Author author = new Author();
        author.setName(name);
        return authorRepository.save(author);
    }
}
