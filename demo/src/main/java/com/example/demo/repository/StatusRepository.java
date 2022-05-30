package com.example.demo.repository;
import java.util.Optional;
import java.util.UUID;

import com.example.demo.model.EStatus;
import com.example.demo.model.Status;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status,UUID> {
    Optional<Status> findByName(EStatus name);
    
}
