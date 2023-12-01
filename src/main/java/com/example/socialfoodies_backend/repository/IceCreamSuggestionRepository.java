package com.example.socialfoodies_backend.repository;

import com.example.socialfoodies_backend.model.IceCreamSuggestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IceCreamSuggestionRepository extends JpaRepository<IceCreamSuggestion, Integer> {
}
