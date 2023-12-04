package com.example.socialfoodies_backend.repository;

import com.example.socialfoodies_backend.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Integer> {
    int countBySelectedOption_PollOptionID(int pollOptionID);
}
