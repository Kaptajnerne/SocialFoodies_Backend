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

    public Vote castVote(int pollOptionID, String email) {
        Vote createdVote = new Vote();

        //Get the customer or else create new customer
        Customer customer = customerRepository.findByEmail(email.toLowerCase()).orElseGet(() -> {
            Customer newCustomer = new Customer();
            newCustomer.setEmail(email.toLowerCase());
            return customerRepository.save(newCustomer);
        });
        //Get the poll option
        PollOption pollOption = pollOptionsRepository.findById(pollOptionID).orElseThrow(() -> new RuntimeException("Poll Option not found"));
        //Get the poll where the vote was taken
        Poll poll = Optional.ofNullable(pollOption.getPoll()).orElseThrow(() -> new RuntimeException("Poll not found for the given Poll Option"));

        createdVote.setCustomer(customer);
        createdVote.setPoll(poll);
        createdVote.setSelectedOption(pollOption);
        voteRepository.save(createdVote);

        return createdVote;
    }

    public int getTotalVotesByPollOptionID(int pollOptionID) {
        return voteRepository.countBySelectedOptionPollOptionID(pollOptionID);
    }

    @Transactional
    public Vote castVotesAndUpdatePollOptions(Poll poll, VoteData voteData) {
            Vote vote = castVote(voteData.getPollOptionId(), voteData.getEmail());

        //Sets and updates the votes
        List<PollOption> updatedOptions = new ArrayList<>();
        for (PollOption option : poll.getPollOptions()) {
            int totalVotes = getTotalVotesByPollOptionID(option.getPollOptionID());
            option.setTotalVotes(totalVotes);
            updatedOptions.add(option);
        }
        pollOptionsRepository.saveAll(updatedOptions);

        return vote;
    }
}