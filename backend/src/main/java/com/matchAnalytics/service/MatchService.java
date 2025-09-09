package com.matchAnalytics.service;

import com.matchAnalytics.model.Match;
import com.matchAnalytics.repository.MatchRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MatchService {
    private final MatchRepository matchRepository;

    public MatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public Optional<Match> getMatch(Long id) {
        return matchRepository.findById(id);
    }
}