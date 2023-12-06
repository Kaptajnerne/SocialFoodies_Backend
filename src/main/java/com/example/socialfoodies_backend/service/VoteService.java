package com.example.socialfoodies_backend.service;

import com.example.socialfoodies_backend.dto.VoteData;
import com.example.socialfoodies_backend.model.Customer;
import com.example.socialfoodies_backend.model.Poll;
import com.example.socialfoodies_backend.model.PollOption;
import com.example.socialfoodies_backend.model.Vote;
import com.example.socialfoodies_backend.repository.CustomerRepository;
import com.example.socialfoodies_backend.repository.PollOptionsRepository;
import com.example.socialfoodies_backend.repository.PollRepository;
import com.example.socialfoodies_backend.repository.VoteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class VoteService {

    @Autowired
    VoteRepository voteRepository;

    @Autowired
    PollOptionsRepository pollOptionsRepository;

    @Autowired
    PollRepository pollRepository;

    @Autowired
    CustomerRepository customerRepository;

    public Vote castVote(VoteData voteData) {
        Vote createdVote = new Vote();

        //Get the customer or else create new customer
        Customer customer = customerRepository.findByEmail(voteData.getEmail().toLowerCase()).orElseGet(() -> {
            Customer newCustomer = new Customer();
            newCustomer.setEmail(voteData.getEmail().toLowerCase());
            return customerRepository.save(newCustomer);
        });
        //Get the poll option
        PollOption pollOption = pollOptionsRepository.findById(voteData.getPollOptionId()).orElseThrow(() -> new RuntimeException("Poll Option not found"));
        //Get the poll where the vote was taken
        Poll poll = Optional.ofNullable(pollOption.getPoll()).orElseThrow(() -> new RuntimeException("Poll not found for the given Poll Option"));

        createdVote.setCustomer(customer);
        createdVote.setPoll(poll);
        createdVote.setSelectedOption(pollOption);
        voteRepository.save(createdVote);

        return createdVote;
    }

    public void updatePollOptionTotalVotes(Poll poll) {
        List<PollOption> pollOptions = pollOptionsRepository.findByPollPollID(poll.getPollID());

        for (PollOption option : pollOptions) {
            int totalVotes = voteRepository.countBySelectedOption_PollOptionID(option.getPollOptionID());
            option.setTotalVotes(totalVotes);
        }
        pollOptionsRepository.saveAll(pollOptions);
    }


    @Transactional
    public Vote castVotesAndUpdatePollOptions(Poll poll, VoteData voteData) {
        Vote vote = castVote(voteData);
        updatePollOptionTotalVotes(poll);

        return vote;
    }


}