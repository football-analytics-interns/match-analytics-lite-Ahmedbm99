package com.matchAnalytics.repository;

import com.matchAnalytics.model.Player;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
        List<Player> findByMatchId(Long matchId);

}
