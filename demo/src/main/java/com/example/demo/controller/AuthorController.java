package com.example.demo.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.UUID;

import com.example.demo.model.Author;
import com.example.demo.repository.AuthorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/author")
@AllArgsConstructor
public class AuthorController {
    
    @Autowired
    private final AuthorRepository authorRepository;

    @PostMapping(path = "/upload")
    public Author uploadProfileImage(@RequestParam("image") MultipartFile file, @RequestParam("authorId") UUID authorId){

        try{
            Author author = authorRepository.findById(authorId).get();

            String encodedString = Base64.getEncoder().encodeToString(file.getBytes());

            author.setProfileImg(encodedString);
            return authorRepository.save(author);
            
        } catch (IOException exception){
            throw new IllegalStateException("Could not add the current file", exception);
        }
    }

    @GetMapping(path = "/image")
    public byte[] getProfileImage(@RequestParam("authorId") UUID authorId){

        Author author = authorRepository.findById(authorId).get();

        byte[] decodedBytes = Base64.getDecoder().decode(author.getProfileImg());

        return decodedBytes;
    }
}
