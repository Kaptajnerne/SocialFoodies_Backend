package com.example.socialfoodies_backend.service;

import com.example.socialfoodies_backend.dto.PollData;
import com.example.socialfoodies_backend.model.Poll;
import com.example.socialfoodies_backend.model.PollOption;
import com.example.socialfoodies_backend.repository.PollOptionsRepository;
import com.example.socialfoodies_backend.repository.PollRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

    @Transactional
    public Poll createAndSetupPoll(PollData pollData) {
        Poll poll = new Poll();
        poll.setStartDate(pollData.getStartDate());
        poll.setEndDate(pollData.getEndDate());

        Set<PollOption> selectedOptions = selectPollOptions(pollData.getPollOptionsIds());
        poll.setPollOptions(selectedOptions);
        pollRepository.save(poll);

        //Set PollOptions FK of poll to the current poll
        for (PollOption option : selectedOptions) {
            option.setPoll(poll);
            pollOptionsRepository.save(option);
        }
        return poll;
    }

    public int getCurrentRunningPollID() {
        LocalDate currentDate = LocalDate.now();

        List<Poll> allPolls = pollRepository.findAll();
        for (Poll poll : allPolls) {
            if (currentDate.isAfter(poll.getStartDate()) && currentDate.isBefore(poll.getEndDate())) {
                return poll.getPollID();
            }
        }
        return 0;
    }

}
