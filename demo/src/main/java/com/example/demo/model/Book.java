package com.example.demo.model;

import java.time.OffsetDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.demo.utils.BookNoGenerator;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "book")
@IdClass(BookId.class)
public class Book{
    
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "book_id")
    private UUID bookId;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_seq")
    @GenericGenerator(
        name = "book_seq",
        strategy = "com.example.demo.utils.BookNoGenerator",
        parameters = {
            @Parameter(name = BookNoGenerator.INCREMENT_PARAM, value = "1")
        }
    )
    @Column(name = "book_no")
    private String bookNo;

    private String name;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    private Boolean isActive;

    private OffsetDateTime dateAdded;
}
