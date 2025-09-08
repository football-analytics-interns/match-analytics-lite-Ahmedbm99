package com.matchAnalytics.repository;

import com.matchAnalytics.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByPlayerId(Long playerId);
}
