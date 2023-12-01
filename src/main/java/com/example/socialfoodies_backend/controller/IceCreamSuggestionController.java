package com.example.socialfoodies_backend.controller;

import com.example.socialfoodies_backend.dto.SuggestionData;
import com.example.socialfoodies_backend.model.Customer;
import com.example.socialfoodies_backend.model.IceCreamSuggestion;
import com.example.socialfoodies_backend.repository.CustomerRepository;
import com.example.socialfoodies_backend.repository.IceCreamSuggestionRepository;
import com.example.socialfoodies_backend.service.IceCreamSuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("suggestion")
@CrossOrigin
public class IceCreamSuggestionController {

    @Autowired
    IceCreamSuggestionRepository iceCreamSuggestionRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    IceCreamSuggestionService iceCreamSuggestionService;

    @PostMapping()
    public ResponseEntity<IceCreamSuggestion> createSuggestion(@RequestBody SuggestionData suggestionData) {
        String email = suggestionData.getEmail().toLowerCase();
        IceCreamSuggestion iceCreamSuggestion = iceCreamSuggestionService.makeSuggestion(email, suggestionData);
        return ResponseEntity.status(HttpStatus.CREATED).body(iceCreamSuggestion);
    }

    @GetMapping()
    public ResponseEntity<List<IceCreamSuggestion>> findAll() {
        List<IceCreamSuggestion> suggestions = iceCreamSuggestionRepository.findAll();
        return ResponseEntity.ok().body(suggestions);
    }
}
