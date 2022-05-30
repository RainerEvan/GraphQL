// package com.example.demo.graphql.resolver;

// import java.util.List;

// import com.coxautodev.graphql.tools.GraphQLResolver;
// import com.example.demo.model.Author;
// import com.example.demo.model.Book;
// import com.example.demo.repository.BookRepository;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Component;

// import lombok.AllArgsConstructor;

// @Component
// @AllArgsConstructor
// public class AuthorResolver implements GraphQLResolver<Author>{

//     @Autowired
//     private final BookRepository bookRepository;

//     public List<Book> getBooks(Author author){
//         return bookRepository.findAllByAuthorAndIsActive(author, true);
//     }
    
// }
