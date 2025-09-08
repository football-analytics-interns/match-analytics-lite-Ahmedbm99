package com.matchAnalytics.repository;

import com.matchAnalytics.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<Match, Long> {
}
