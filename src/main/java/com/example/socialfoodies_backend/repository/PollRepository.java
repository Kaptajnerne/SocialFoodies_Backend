package com.example.socialfoodies_backend.repository;

import com.example.socialfoodies_backend.model.Poll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PollRepository extends JpaRepository<Poll, Integer> {
    Poll findById(int pollId);
}

