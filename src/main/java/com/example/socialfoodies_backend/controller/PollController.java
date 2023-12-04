package com.example.socialfoodies_backend.controller;

import com.example.socialfoodies_backend.dto.VoteData;
import com.example.socialfoodies_backend.model.*;
import com.example.socialfoodies_backend.repository.CustomerIceCreamRepository;
import com.example.socialfoodies_backend.repository.IceCreamRepository;
import com.example.socialfoodies_backend.repository.PollOptionsRepository;
import com.example.socialfoodies_backend.repository.PollRepository;
import com.example.socialfoodies_backend.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("poll")
@CrossOrigin
public class PollController {

    @Autowired
    private PollRepository pollRepository;

    @Autowired
    PollOptionsRepository pollOptionsRepository;

    @Autowired
    VoteService voteService;


    @PostMapping("/{pollOptionID}/{email}/{pollID}")
    public ResponseEntity<Vote> makeAVote(@PathVariable int pollOptionID, @PathVariable String email, @PathVariable int pollID) {
        Poll poll = pollRepository.findById(pollID);
        VoteData voteData = new VoteData(pollOptionID, email);

        Vote vote = voteService.castVotesAndUpdatePollOptions(poll, voteData);
        return ResponseEntity.status(HttpStatus.CREATED).body(vote);
    }



    @GetMapping("/{id}")
    public List<PollOption> getPollOptionsByPoll(@PathVariable int id) {
        return pollOptionsRepository.findByPollPollID(id);
    }

    @GetMapping()
    public ResponseEntity<List<Poll>> findAll() {
        List<Poll> polls = pollRepository.findAll();
        return ResponseEntity.ok().body(polls);
    }


    @PostMapping()
    public ResponseEntity<Poll> create(@RequestBody Poll poll) {
        Poll createdPoll = pollRepository.save(poll);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPoll);
    }
}
