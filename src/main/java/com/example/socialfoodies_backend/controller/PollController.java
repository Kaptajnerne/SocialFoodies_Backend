package com.example.socialfoodies_backend.controller;

import com.example.socialfoodies_backend.model.CustomerIceCream;
import com.example.socialfoodies_backend.model.IceCream;
import com.example.socialfoodies_backend.model.Poll;
import com.example.socialfoodies_backend.repository.CustomerIceCreamRepository;
import com.example.socialfoodies_backend.repository.IceCreamRepository;
import com.example.socialfoodies_backend.repository.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/poll")
@CrossOrigin
public class PollController {

    @Autowired
    private PollRepository pollRepository;

    @GetMapping()
    public ResponseEntity<List<Poll>> findAll() {
        List<Poll> polls = pollRepository.findAll();
        return ResponseEntity.ok().body(polls);
    }

    /*
    @GetMapping("/{id}")
    public ResponseEntity<Poll> findById(@PathVariable int id) {
        Optional<Poll> poll = pollRepository.findById(id);
        return poll.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    */

    @PostMapping()
    public ResponseEntity<Poll> create(@RequestBody Poll poll) {
        Poll createdPoll = pollRepository.save(poll);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPoll);
    }
}
