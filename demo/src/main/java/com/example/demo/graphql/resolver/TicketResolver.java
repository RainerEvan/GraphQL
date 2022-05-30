package com.example.demo.graphql.resolver;

import java.util.List;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.example.demo.model.Support;
import com.example.demo.model.Ticket;
import com.example.demo.repository.SupportRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class TicketResolver implements GraphQLResolver<Ticket> {
    
    @Autowired
    private final SupportRepository supportRepository;

    public List<Support> getSupport(Ticket ticket){
        
        return supportRepository.findAllByTicketAndIsActive(ticket,true);
    }
}
