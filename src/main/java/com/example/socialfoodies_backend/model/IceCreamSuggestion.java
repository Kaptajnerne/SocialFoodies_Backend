package com.example.socialfoodies_backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class IceCreamSuggestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int iceCreamSuggestionID;
    private String name;
    private String description;
    private boolean vegan;
    private boolean nuts;

    @ManyToOne
    @JoinColumn(name = "customerID")
    private Customer customer;
}
