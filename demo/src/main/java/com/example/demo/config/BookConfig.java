// package com.example.demo.config;

// import java.time.OffsetDateTime;

// import com.example.demo.model.Author;
// import com.example.demo.model.Book;
// import com.example.demo.model.ERole;
// import com.example.demo.model.Role;
// import com.example.demo.repository.AuthorRepository;
// import com.example.demo.repository.BookRepository;
// import com.example.demo.repository.RoleRepository;

// import org.springframework.boot.CommandLineRunner;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// @Configuration
// public class BookConfig {
        
//     @Bean
//     CommandLineRunner commandLineRunner(BookRepository bookRepository, AuthorRepository authorRepository, RoleRepository roleRepository){
//         return args -> {

//             Author author = new Author();
//             author.setName("Rainer");
//             authorRepository.save(author);

//             Role role = new Role();
//             role.setName(ERole.ADMIN);
//             roleRepository.save(role);

//             Role role2 = new Role();
//             role2.setName(ERole.USER);
//             roleRepository.save(role2);

//             Role role3 = new Role();
//             role3.setName(ERole.DEVELOPER);
//             roleRepository.save(role3);

//             Book book = new Book();
//             book.setName("Harry Potter and The Black Swan");
//             book.setAuthor(author);
//             book.setIsActive(true);
//             book.setDateAdded(OffsetDateTime.now());
//             bookRepository.save(book);

//             Book book2 = new Book();
//             book2.setName("Hunger Games");
//             book2.setAuthor(author);
//             book2.setIsActive(false);
//             book2.setDateAdded(OffsetDateTime.now());
//             bookRepository.save(book2);

//             Book book3 = new Book();
//             book3.setName("Alvin and The Band");
//             book3.setAuthor(author);
//             book3.setIsActive(false);
//             book3.setDateAdded(OffsetDateTime.now());
//             bookRepository.save(book3);
//         };
//     }
// }
