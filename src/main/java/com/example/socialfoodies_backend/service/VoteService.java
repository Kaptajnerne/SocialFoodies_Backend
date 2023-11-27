package com.example.socialfoodies_backend.service;

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

        //Get the customer
        Customer customer = customerRepository.findByEmail(email).orElseGet(() -> {
            Customer newCustomer = new Customer();
            newCustomer.setEmail(email);
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


    public void updateVoteCounts(Poll poll) {
        List<PollOption> updatedOptions = new ArrayList<>();
        for (PollOption option : poll.getPollOptions()) {
            int totalVotes = getTotalVotesByPollOptionID(option.getPollOptionID());
            option.setTotalVotes(totalVotes);
            updatedOptions.add(option);
        }
        pollOptionsRepository.saveAll(updatedOptions);
    }

    @Transactional
    public void castVoteAndUpdateCounts(int pollOptionID, String email) {
        Vote vote = castVote(pollOptionID, email);

        Poll refreshedPoll = pollRepository.findById(vote.getSelectedOption().getPoll().getPollID()).orElseThrow(() -> new RuntimeException("Poll not found"));
        updateVoteCounts(refreshedPoll);
    }

}