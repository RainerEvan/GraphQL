package com.example.demo.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.demo.model.Author;
import com.example.demo.model.Ticket;
import com.example.demo.model.TicketPK;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,TicketPK> {
    Optional<Ticket> findByTicketId(UUID id);
    Optional<Ticket> findByTicketNo(String ticketNo);
    List<Ticket> findAllByReporter(Author reporter);
}
