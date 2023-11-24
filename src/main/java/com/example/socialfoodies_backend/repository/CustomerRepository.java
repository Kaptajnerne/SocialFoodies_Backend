package com.example.socialfoodies_backend.repository;

import com.example.socialfoodies_backend.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    // You can add custom query methods here if needed
}
