package com.example.socialfoodies_backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
public class CustomerIceCream {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerIceCreamID;
    private String name;
    private String description;
    private boolean vegan;
    private boolean nuts;
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "customerID")
    private Customer customer;
}
