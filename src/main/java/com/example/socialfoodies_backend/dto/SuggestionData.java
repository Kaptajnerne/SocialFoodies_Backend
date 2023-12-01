package com.example.socialfoodies_backend.dto;

import lombok.Data;

@Data
public class SuggestionData {
    private String name;
    private String description;
    private boolean vegan;
    private boolean nuts;
    private String email;
}
