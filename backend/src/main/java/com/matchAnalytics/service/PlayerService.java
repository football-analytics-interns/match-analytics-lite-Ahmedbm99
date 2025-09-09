package com.matchAnalytics.service;

import com.matchAnalytics.dto.PlayerStatsDTO;
import com.matchAnalytics.model.Event;
import com.matchAnalytics.model.Player;
import com.matchAnalytics.repository.EventRepository;
import com.matchAnalytics.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final EventRepository eventRepository;

    public PlayerService(PlayerRepository playerRepository, EventRepository eventRepository) {
        this.playerRepository = playerRepository;
        this.eventRepository = eventRepository;
    }

    public PlayerStatsDTO getPlayerStats(Long playerId) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new RuntimeException("Player not found"));

        List<Event> events = eventRepository.findByPlayerId(playerId);

        int goals = (int) events.stream()
                .filter(e -> e.getType().equalsIgnoreCase("goal"))
                .count();

        int assists = (int) events.stream()
                .filter(e -> e.getType().equalsIgnoreCase("pass") && e.getMeta().contains("assistId"))
                .count();

        double rating = goals * 3 + assists * 2 + (events.size() - goals - assists) * 0.5;

        return new PlayerStatsDTO(player.getId(), player.getName(), goals, assists, rating);
    }
}
