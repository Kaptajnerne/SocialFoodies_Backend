package com.example.socialfoodies_backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class IceCream {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int iceCreamID;
    private String name;
    private String description;
    private boolean vegan;
    private boolean nuts;
}