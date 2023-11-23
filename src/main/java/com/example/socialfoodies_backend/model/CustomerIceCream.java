package com.example.socialfoodies_backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class CustomerIceCream {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerIceCreamID;
    private String name;
    private String description;
    private boolean vegan;
    private boolean nuts;

    @ManyToOne
    @JoinColumn(name = "customerID")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "pollID")
    private Poll poll;
}
