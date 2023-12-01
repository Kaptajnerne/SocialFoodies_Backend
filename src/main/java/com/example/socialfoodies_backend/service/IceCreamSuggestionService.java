package com.example.socialfoodies_backend.service;

import com.example.socialfoodies_backend.dto.SuggestionData;
import com.example.socialfoodies_backend.model.Customer;
import com.example.socialfoodies_backend.model.IceCreamSuggestion;
import com.example.socialfoodies_backend.repository.CustomerRepository;
import com.example.socialfoodies_backend.repository.IceCreamSuggestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IceCreamSuggestionService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    IceCreamSuggestionRepository iceCreamSuggestionRepository;

    public IceCreamSuggestion makeSuggestion(String email, SuggestionData suggestionData) {
        Customer customer = customerRepository.findByEmail(email).orElseGet(() -> {
            Customer newCustomer = new Customer();
            newCustomer.setEmail(email);
            return customerRepository.save(newCustomer);
        });

        IceCreamSuggestion iceCreamSuggestion = new IceCreamSuggestion();
        iceCreamSuggestion.setName(suggestionData.getName());
        iceCreamSuggestion.setDescription(suggestionData.getDescription());
        iceCreamSuggestion.setVegan(suggestionData.isVegan());
        iceCreamSuggestion.setNuts(suggestionData.isNuts());
        iceCreamSuggestion.setCustomer(customer);

        return iceCreamSuggestionRepository.save(iceCreamSuggestion);
    }

}