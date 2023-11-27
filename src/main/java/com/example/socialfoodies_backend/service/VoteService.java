package com.example.socialfoodies_backend.service;

import com.example.socialfoodies_backend.model.Customer;
import com.example.socialfoodies_backend.model.Poll;
import com.example.socialfoodies_backend.model.PollOption;
import com.example.socialfoodies_backend.model.Vote;
import com.example.socialfoodies_backend.repository.CustomerRepository;
import com.example.socialfoodies_backend.repository.PollOptionsRepository;
import com.example.socialfoodies_backend.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VoteService {

    @Autowired
    PollOptionsRepository pollOptionsRepository;

    @Autowired
    CustomerRepository customerRepository;

    public Vote castVote(int pollOptionID, String email) {
        Vote createdVote = new Vote();

        Customer customer = customerRepository.findByEmail(email).orElseGet(() -> {
            Customer newCustomer = new Customer();
            newCustomer.setEmail(email);
            return customerRepository.save(newCustomer);
        });

        PollOption pollOption = pollOptionsRepository.findById(pollOptionID).orElseThrow(() -> new RuntimeException("Poll Option not found"));

        Poll poll = Optional.ofNullable(pollOption.getPoll()).orElseThrow(() -> new RuntimeException("Poll not found for the given Poll Option"));

        createdVote.setCustomer(customer);
        createdVote.setPoll(poll);
        createdVote.setSelectedOption(pollOption);

        return createdVote;
    }

}