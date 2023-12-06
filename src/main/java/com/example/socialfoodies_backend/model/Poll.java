package com.example.socialfoodies_backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
public class Poll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pollID;
    private LocalDate startDate;
    private LocalDate endDate;

    @OneToMany(mappedBy = "poll")
    private Set<PollOption> pollOptions = new HashSet<>();

    @Override
    public int hashCode() {
        return Objects.hash(pollID, startDate, endDate);
    }
}

