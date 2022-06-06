package com.example.demo.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.EStatus;
import com.example.demo.model.Ticket;
import com.example.demo.service.TicketAttachmentService;
import com.example.demo.service.TicketService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/ticket")
@AllArgsConstructor
public class TicketController {
    
    @Autowired
    private final TicketService ticketService;
    @Autowired
    private final TicketAttachmentService ticketAttachmentService;

    @PostMapping(path = "/add")
    public ResponseEntity<String> addTicket(@RequestPart("title") String title, @RequestPart("description") String description, @RequestPart("file") String file){

        Ticket ticket = ticketService.addTicket(title,description);

        ticketAttachmentService.addFile(ticket, file);

        return ResponseEntity.status(HttpStatus.OK).body("Ticket created successfully");
    }

    @PutMapping(path ="/update")
    public ResponseEntity<String> updateTicketStatus(@RequestParam("ticketId") UUID ticketId, @RequestParam("status") EStatus status){
        Ticket ticket = ticketService.updateTicket(ticketId, status);

        return ResponseEntity.status(HttpStatus.OK).body("Ticket status has been updated: "+ticket.getTicketNo());
    }

}
