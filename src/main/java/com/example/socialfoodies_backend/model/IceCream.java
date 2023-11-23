package com.example.socialfoodies_backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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