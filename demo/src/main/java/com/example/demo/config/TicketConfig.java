package com.example.demo.config;

import java.time.LocalDate;

import com.example.demo.model.Author;
import com.example.demo.model.EStatus;
import com.example.demo.model.Status;
import com.example.demo.model.Support;
import com.example.demo.model.Ticket;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.StatusRepository;
import com.example.demo.repository.SupportRepository;
import com.example.demo.repository.TicketRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TicketConfig {
    @Bean
    CommandLineRunner commandLineRunner(AuthorRepository authorRepository, StatusRepository statusRepository, TicketRepository ticketRepository, SupportRepository supportRepository){
        return args -> {

            Author author = new Author();
            author.setName("Maman");
            authorRepository.save(author);

            Status status1 = new Status();
            status1.setName(EStatus.PENDING);
            statusRepository.save(status1);

            Status status2 = new Status();
            status2.setName(EStatus.IN_PROGRESS);
            statusRepository.save(status2);

            Status status3 = new Status();
            status3.setName(EStatus.RESOLVED);
            statusRepository.save(status3);

            Status status4 = new Status();
            status4.setName(EStatus.CLOSED);
            statusRepository.save(status4);

            Status status5 = new Status();
            status5.setName(EStatus.DROPPED);
            statusRepository.save(status5);
           
            // Ticket ticket = new Ticket();
            // ticket.setDateAdded(LocalDate.now());
            // ticket.setReporter(author);
            // ticket.setTitle("Aplikasi CAMS error");
            // ticket.setDescription("Form gabisa diisi lagi gimana dong");
            // ticket.setStatus(status1);
            // ticketRepository.save(ticket);

            // Support support = new Support();
            // support.setTicket(ticket);
            // support.setDateTaken(LocalDate.now());
            // support.setDeveloper(author);
            // support.setIsActive(true);
            // supportRepository.save(support);

            // Support support2 = new Support();
            // support2.setTicket(ticket);
            // support2.setDateTaken(LocalDate.now());
            // support2.setDeveloper(author);
            // support2.setIsActive(false);
            // supportRepository.save(support2);

        };
    }
}
