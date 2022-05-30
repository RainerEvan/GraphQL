package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.example.demo.exception.AbstractGraphQLException;
import com.example.demo.model.Author;
import com.example.demo.model.Support;
import com.example.demo.model.Ticket;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.SupportRepository;
import com.example.demo.repository.TicketRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SupportService {
    
    @Autowired
    private final SupportRepository supportRepository;
    @Autowired
    private final TicketRepository ticketRepository;
    @Autowired
    private final AuthorRepository authorRepository;

    public Support getSupport(UUID supportId){
        return supportRepository.findById(supportId).orElseThrow(() -> new AbstractGraphQLException("Support with current id cannot be found: "+supportId, "supportId"));
    }
    
    public List<Support> getSupportForTicket(UUID ticketId){

        Ticket ticket = ticketRepository.findByTicketId(ticketId)
            .orElseThrow(() -> new AbstractGraphQLException("Ticket with current id cannot be found: "+ticketId, "ticketId"));

        return supportRepository.findAllByTicketAndIsActive(ticket,true);
    }

    public List<Support> getAllSupportsForDeveloper(UUID authorId){

        Author developer = authorRepository.findById(authorId)
            .orElseThrow(() -> new AbstractGraphQLException("author with current id cannot be found: "+authorId,"authorId"));
        
        return supportRepository.findAllByDeveloper(developer);
    }

    public Support addSupport(UUID ticketId) {

        Ticket ticket = ticketRepository.findByTicketId(ticketId)
            .orElseThrow(() -> new AbstractGraphQLException("Ticket with current id cannot be found: "+ticketId, "ticketId")); 

        Support support = new Support();        
        support.setTicket(ticket);
        support.setDateTaken(LocalDate.now());
        support.setIsActive(true);

        return supportRepository.save(support);
    }

    public Support solveSupport(UUID supportId, String result, String description) {
        
        Support support = supportRepository
            .findById(supportId).orElseThrow(() -> new AbstractGraphQLException("Support with current id cannot be found: "+supportId, "supportId"));

        support.setResult(result);
        support.setDescription(description);

        return supportRepository.save(support);
    }

    public Support withdrawSupport(UUID supportId, String result, String description) {
        Support support = supportRepository
            .findById(supportId).orElseThrow(() -> new AbstractGraphQLException("Support with current id cannot be found: "+supportId, "supportId"));

        support.setResult(result);
        support.setDescription(description);

        return supportRepository.save(support);
    }

    public Support reassignSupport(UUID ticketId, UUID currSupportId, UUID developerId){
        Support currSupport = supportRepository.findById(currSupportId)
            .orElseThrow(() -> new AbstractGraphQLException("Support with current id cannot be found: "+currSupportId, "supportId"));
        currSupport.setIsActive(false);
        supportRepository.save(currSupport);
        
        Ticket ticket = ticketRepository.findByTicketId(ticketId)
            .orElseThrow(() -> new AbstractGraphQLException("Ticket with current id cannot be found: "+ticketId, "ticketId"));

        Author developer = authorRepository.findById(developerId)
            .orElseThrow(() -> new AbstractGraphQLException("author with current id cannot be found: "+developerId,"authorId"));

        Support newSupport = new Support();          
        newSupport.setTicket(ticket);
        newSupport.setDateTaken(LocalDate.now());
        newSupport.setDeveloper(developer);
        newSupport.setIsActive(true);

        return supportRepository.save(newSupport);
    }

}
