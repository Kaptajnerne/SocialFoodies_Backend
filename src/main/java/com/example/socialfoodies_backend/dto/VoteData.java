package com.example.socialfoodies_backend.dto;

import lombok.Data;

@Data
public class VoteData {
    private final int pollOptionId;
    private final String email;
}
