package com.example.socialfoodies_backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "customerID")
    private Customer customer;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "pollID")
    private Poll poll;

    @ManyToOne
    @JoinColumn(name = "pollOptionID")
    private PollOption selectedOption;
}