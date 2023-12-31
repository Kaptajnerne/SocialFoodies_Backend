package com.example.socialfoodies_backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class PollOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pollOptionID;

    @ManyToOne
    @JoinColumn(name = "iceCreamID")
    private IceCream iceCream;

    @ManyToOne
    @JoinColumn(name = "customerIceCreamID")
    private CustomerIceCream customerIceCream;

    @ManyToOne
    @JoinColumn(name = "pollID")
    private Poll poll;

    //Property to count the amount of votes an option gets
    //Will most likely need business logic to do this (Method in service class)
}
