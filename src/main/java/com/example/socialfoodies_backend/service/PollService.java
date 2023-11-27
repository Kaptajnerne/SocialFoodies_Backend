package com.example.socialfoodies_backend.service;

import com.example.socialfoodies_backend.model.Poll;
import com.example.socialfoodies_backend.model.PollOption;
import com.example.socialfoodies_backend.repository.PollOptionsRepository;
import com.example.socialfoodies_backend.repository.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Service
public class PollService {

    @Autowired
    private PollOptionsRepository pollOptionsRepository;

    @Autowired
    private PollRepository pollRepository;

    public Set<PollOption> selectPollOptions(int[] optionIds) {
        Set<PollOption> selectedPollOptions = new HashSet<>();
        for (int optionId : optionIds) {
            PollOption option = pollOptionsRepository.findById(optionId).orElseThrow(() -> new RuntimeException("Poll Option not found"));
            selectedPollOptions.add(option);
        }
        return selectedPollOptions;
    }

    public Poll createAndSetupPoll(LocalDate startDate, LocalDate endDate, int[] optionIds) {
        Poll poll = new Poll();
        poll.setStartDate(startDate);
        poll.setEndDate(endDate);

        Set<PollOption> selectedOptions = selectPollOptions(optionIds);
        poll.setPollOptions(selectedOptions);
        pollRepository.save(poll);

        //Set PollOptions FK of poll to the current poll
        for (PollOption option : selectedOptions) {
            option.setPoll(poll);
            pollOptionsRepository.save(option);
        }
        return poll;
    }

}
