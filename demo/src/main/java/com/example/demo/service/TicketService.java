package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.example.demo.exception.AbstractGraphQLException;
import com.example.demo.model.Author;
import com.example.demo.model.EStatus;
import com.example.demo.model.Status;
import com.example.demo.model.Ticket;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.StatusRepository;
import com.example.demo.repository.TicketRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TicketService {
    
    @Autowired
    private final TicketRepository ticketRepository;
    @Autowired
    private final AuthorRepository authorRepository;
    @Autowired
    private final StatusRepository statusRepository;

    public List<Ticket> getAllTickets(){
        return ticketRepository.findAll();
    }

    public Ticket getTicket(UUID ticketId){
        return ticketRepository.findByTicketId(ticketId)
            .orElseThrow(() -> new AbstractGraphQLException("Ticket with current id cannot be found: "+ticketId, "ticketId"));
    }

    public List<Ticket> getAllTicketsForUser(UUID authorId){
        Author user = authorRepository.findById(authorId)
            .orElseThrow(() -> new AbstractGraphQLException("Author with current id cannot be found: "+authorId,"authorId"));

        return ticketRepository.findAllByReporter(user);
    }

    public Ticket addTicket(String title, String description){

        Ticket ticket = new Ticket();
        ticket.setTitle(title);
        ticket.setDescription(description);
        ticket.setDateAdded(LocalDate.now());

        Status status = statusRepository.findByName(EStatus.PENDING)
            .orElseThrow(() -> new AbstractGraphQLException("Status with current name cannot be found: "+EStatus.PENDING, "statusName"));

        ticket.setStatus(status);

        return ticketRepository.save(ticket);
    }

    public Ticket updateTicket(UUID ticketId, EStatus name){
        Ticket ticket = ticketRepository.findByTicketId(ticketId).orElseThrow(() -> new AbstractGraphQLException("Ticket with current id cannot be found: "+ticketId, "ticketId"));

        Status status = statusRepository.findByName(name)
            .orElseThrow(() -> new AbstractGraphQLException("Status with current name cannot be found: "+name, "statusName"));

        ticket.setStatus(status);
        return ticketRepository.save(ticket);
    }

    public Ticket closeTicket(UUID ticketId, EStatus name){
        Ticket ticket = ticketRepository.findByTicketId(ticketId).orElseThrow(() -> new AbstractGraphQLException("Ticket with current id cannot be found: "+ticketId, "ticketId"));
        
        Status status = statusRepository.findByName(name)
            .orElseThrow(() -> new AbstractGraphQLException("Status with current name cannot be found: "+name, "statusName"));

        ticket.setStatus(status);
        ticket.setDateClosed(LocalDate.now());

        return ticketRepository.save(ticket);
    }

    public void deleteTicket(UUID ticketId){
        ticketRepository.delete(getTicket(ticketId));
    }
}
