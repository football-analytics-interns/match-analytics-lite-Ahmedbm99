package com.matchAnalytics.repository;

import com.matchAnalytics.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
