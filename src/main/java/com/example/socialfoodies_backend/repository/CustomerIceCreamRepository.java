package com.example.socialfoodies_backend.repository;

import com.example.socialfoodies_backend.model.CustomerIceCream;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerIceCreamRepository extends JpaRepository<CustomerIceCream, Integer> {
    // You can add custom query methods here if needed
}

