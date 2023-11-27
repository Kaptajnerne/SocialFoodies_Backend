package com.example.socialfoodies_backend.service;

import com.example.socialfoodies_backend.model.PollOption;
import com.example.socialfoodies_backend.repository.PollOptionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class PollService {

    @Autowired
    private PollOptionsRepository pollOptionsRepository;

    public Set<PollOption> selectPollOptions(int option1, int option2, int option3) {
        Set<PollOption> selectedPollOptions = new HashSet<>();

        PollOption pollOption1 = pollOptionsRepository.findById(option1).orElse(null);
        PollOption pollOption2 = pollOptionsRepository.findById(option2).orElse(null);
        PollOption pollOption3 = pollOptionsRepository.findById(option3).orElse(null);

        selectedPollOptions.add(pollOption1);
        selectedPollOptions.add(pollOption2);
        selectedPollOptions.add(pollOption3);

        return selectedPollOptions;
    }
}
