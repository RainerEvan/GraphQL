package com.example.demo.repository;

import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

import com.example.demo.model.Author;
import com.example.demo.model.Support;
import com.example.demo.model.Ticket;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface SupportRepository extends JpaRepository<Support, UUID>{
    List<Support> findAllByTicketAndIsActive(Ticket ticket, Boolean isActive);
    List<Support> findAllByDeveloper(Author developer);
}
