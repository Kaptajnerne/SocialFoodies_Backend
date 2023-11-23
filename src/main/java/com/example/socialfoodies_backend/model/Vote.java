package com.example.socialfoodies_backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int votedOption;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "customerID")
    private Customer customer;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "pollID")
    private Poll poll;

}