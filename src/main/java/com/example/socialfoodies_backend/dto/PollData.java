package com.example.socialfoodies_backend.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PollData {
    private LocalDate startDate;
    private LocalDate endDate;
    private int[] pollOptionsIds;

    public PollData(LocalDate startDate, LocalDate endDate, int[] pollOptionsIds) {
        this.startDate=startDate;
        this.endDate=endDate;
        this.pollOptionsIds=pollOptionsIds;
    }
}
