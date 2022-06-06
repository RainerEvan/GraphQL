package com.example.demo.model;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.example.demo.utils.BookNoGenerator;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@IdClass(TicketPK.class)
public class Ticket extends TicketLog<String>{

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "ticket_id")
    private UUID ticketId;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ticket_seq")
    @GenericGenerator(
        name = "ticket_seq",
        strategy = "com.example.demo.utils.BookNoGenerator",
        parameters = {@Parameter(name = BookNoGenerator.INCREMENT_PARAM, value = "50")}
    )
    @Column(name = "ticket_no")
    private String ticketNo;

    private LocalDate dateAdded;

    @ManyToOne
    @JoinColumn(name="reporter_id")
    private Author reporter;

    private String title;
    private String description;
    private LocalDate dateClosed;

    @ManyToOne
    @JoinColumn(name="status_id")
    private Status status;

    @OneToMany(mappedBy = "ticket")
    private List<Support> support;

}
