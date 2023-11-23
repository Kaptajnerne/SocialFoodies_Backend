package com.example.socialfoodies_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Poll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pollID;
    private Date startDate;
    private Date endDate;
    private int iceCreamOption1;
    private int iceCreamOption2;
    private int iceCreamOption3;

    @OneToMany(mappedBy = "poll")
    @JsonIgnore
    private Set<IceCream> iceCreams = new HashSet<>();

    @OneToMany(mappedBy = "poll")
    @JsonIgnore
    private Set<CustomerIceCream> customerIceCreams = new HashSet<>();
}
