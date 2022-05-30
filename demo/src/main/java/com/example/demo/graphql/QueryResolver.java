package com.example.demo.graphql;

import java.util.List;
import java.util.UUID;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.demo.exception.AbstractGraphQLException;
import com.example.demo.model.Author;
import com.example.demo.model.Book;
import com.example.demo.model.ERole;
import com.example.demo.model.Role;
import com.example.demo.model.Support;
import com.example.demo.model.Ticket;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.SupportService;
import com.example.demo.service.TicketService;

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
    @Autowired
    private final RoleRepository roleRepository;

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
            return bookRepository.findByBookId(UUID.fromString(id)).orElseThrow(()->new AbstractGraphQLException("Book with current id not found: "+id,"bookId"));
        }

    }

    public List<Book> getAllBooksForAuthor(UUID authorId){
        
        Author author = authorRepository.findById(authorId).orElseThrow(()->new AbstractGraphQLException("Author with current id not found: "+authorId,"authorId"));
        
        return bookRepository.findAllByAuthorAndIsActive(author, true);
    }

    public List<Role> getAllRole(){
        return roleRepository.findAll();
    }

    public Role getRole(ERole name){
        return roleRepository.findByName(name).orElseThrow(()->new AbstractGraphQLException("Role with current name not found: "+name,"roleName"));
    }

    @Autowired
    private final TicketService ticketService;

    public List<Ticket> getAllTickets(){
        return ticketService.getAllTickets();
    }

    public Ticket getTicket(UUID ticketId){
        return ticketService.getTicket(ticketId);
    }

    public List<Ticket> getAllTicketsForUser(UUID authorId){
        return ticketService.getAllTicketsForUser(authorId);
    }

    @Autowired
    private final SupportService supportService;

    public List<Support> getAllSupportsForDeveloper(UUID accountId){
        return supportService.getAllSupportsForDeveloper(accountId);
    }
}
