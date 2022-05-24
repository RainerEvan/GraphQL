package com.example.demo.repository;

import java.util.Optional;
import java.util.UUID;

import com.example.demo.model.ERole;
import com.example.demo.model.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,UUID>{
    Optional<Role> findByName(ERole name);
}
