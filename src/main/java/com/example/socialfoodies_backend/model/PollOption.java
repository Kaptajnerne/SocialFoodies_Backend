package com.example.socialfoodies_backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
public class PollOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pollOptionID;

    @ManyToOne
    @JoinColumn(name = "iceCreamID", foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
    private IceCream iceCream;

    @ManyToOne
    @JoinColumn(name = "customerIceCreamID")
    private CustomerIceCream customerIceCream;

    @ManyToOne
    @JoinColumn(name = "pollID")
    private Poll poll;

    private int totalVotes;
}
