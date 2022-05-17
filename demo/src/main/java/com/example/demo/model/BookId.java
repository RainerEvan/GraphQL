package com.example.demo.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class BookId implements Serializable{
    
    private Long id;
    private String bookNo;

    
}
