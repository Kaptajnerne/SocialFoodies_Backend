package com.example.socialfoodies_backend.repository;

import com.example.socialfoodies_backend.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Optional<Admin> findByEmailAndPassword(String email, String password);
}
