package com.example.socialfoodies_backend.repository;

import com.example.socialfoodies_backend.model.PollOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PollOptionsRepository extends JpaRepository<PollOption, Integer> {
    List<PollOption> findByPollPollID(int pollID);
}

