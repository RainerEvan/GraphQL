package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Ticket;
import com.example.demo.model.TicketAttachment;
import com.example.demo.repository.TicketAttachmentRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TicketAttachmentService {

    @Autowired
    private final TicketAttachmentRepository ticketAttachmentRepository;
    
    public TicketAttachment addFile(Ticket ticket, String file){

        TicketAttachment ticketAttachment = new TicketAttachment();
        ticketAttachment.setName(file);
        ticketAttachment.setTicket(ticket);
        return ticketAttachmentRepository.save(ticketAttachment);
    }
}
