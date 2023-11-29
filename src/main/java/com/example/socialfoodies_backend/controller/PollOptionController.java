package com.example.socialfoodies_backend.controller;

import com.example.socialfoodies_backend.model.PollOption;
import com.example.socialfoodies_backend.repository.PollOptionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pollOption")
@CrossOrigin
public class PollOptionController {

    @Autowired
    private PollOptionsRepository pollOptionsRepository;

    @GetMapping()
    public ResponseEntity<List<PollOption>> findAll() {
        List<PollOption> pollOptions = pollOptionsRepository.findAll();
        return ResponseEntity.ok().body(pollOptions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PollOption> findById(@PathVariable int id) {
        Optional<PollOption> pollOptions = pollOptionsRepository.findById(id);
        return pollOptions.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<PollOption> create(@RequestBody PollOption pollOptions) {
        PollOption createdPollOption = pollOptionsRepository.save(pollOptions);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPollOption);
    }

}
