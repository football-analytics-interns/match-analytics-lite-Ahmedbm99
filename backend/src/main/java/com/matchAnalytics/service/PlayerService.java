package com.matchAnalytics.service;

import com.matchAnalytics.dto.PlayerStatsDTO;
import com.matchAnalytics.model.Event;
import com.matchAnalytics.model.Player;
import com.matchAnalytics.repository.EventRepository;
import com.matchAnalytics.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

        // Count goals
        long goals = events.stream()
                .filter(e -> "goal".equalsIgnoreCase(e.getType()))
                .count();

        // Count assists: check meta map safely
        long assists = events.stream()
                .filter(e -> {
                    Map<String, Object> meta = e.getMeta();
                    return meta != null && meta.containsKey("assistId");
                })
                .count();

        long totalEvents = events.size();

        System.out.println("Player: " + player.getName() +
                ", Goals: " + goals +
                ", Assists: " + assists +
                ", Total Events: " + totalEvents);

        // Simple rating formula
        double rating = goals * 4 + assists * 2 + (totalEvents - goals - assists) * 0.5;

        return new PlayerStatsDTO(player.getId(), player.getName(), (int) goals, (int) assists, rating);
    }
}
