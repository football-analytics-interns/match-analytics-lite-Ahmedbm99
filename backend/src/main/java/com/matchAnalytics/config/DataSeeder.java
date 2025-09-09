package com.matchAnalytics.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.matchAnalytics.dto.SeedData;
import com.matchAnalytics.model.Event;
import com.matchAnalytics.model.Match;
import com.matchAnalytics.model.Player;
import com.matchAnalytics.repository.EventRepository;
import com.matchAnalytics.repository.MatchRepository;
import com.matchAnalytics.repository.PlayerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Map;

@Component
public class DataSeeder implements CommandLineRunner {

    private final MatchRepository matchRepository;
    private final PlayerRepository playerRepository;
    private final EventRepository eventRepository;
    private final ObjectMapper objectMapper;

    public DataSeeder(MatchRepository matchRepository,
                      PlayerRepository playerRepository,
                      EventRepository eventRepository,
                      ObjectMapper objectMapper) {
        this.matchRepository = matchRepository;
        this.playerRepository = playerRepository;
        this.eventRepository = eventRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        if (matchRepository.count() > 0) {
            System.out.println("Database already seeded.");
            return;
        }

        try (InputStream inputStream = getClass().getResourceAsStream("/seed/match.json")) {

            SeedData seed = objectMapper.readValue(inputStream, SeedData.class);

            // --- Save Match ---
            Match match = new Match();
            match.setId(seed.match.id);
            match.setDate(seed.match.date);
            match.setHomeScore(seed.match.homeScore);
            match.setAwayScore(seed.match.awayScore);
            match.setHomeTeam(seed.match.homeTeam);
            match.setAwayTeam(seed.match.awayTeam);
            matchRepository.save(match);

            // --- Save Players ---
            for (SeedData.PlayerDTO p : seed.players) {
                Player player = new Player();
                player.setId(p.id);
                player.setName(p.name);
                player.setTeam(p.team);
                player.setPosition(p.position);
                player.setMatch(match);
                playerRepository.save(player);
            }

            // --- Save Events ---
            for (SeedData.EventDTO e : seed.events) {
                Event event = new Event();
                event.setMinute(e.minute);
                event.setType(e.type);
                event.setMatch(match);

                // Set player
                Player player = playerRepository.findById(e.playerId)
                        .orElseThrow(() -> new RuntimeException("Player not found for event"));
                event.setPlayer(player);
                event.setPlayerId(player.getId());

                // Set meta if exists (convert JSON object to Map)
                if (e.meta != null) {
                    Map<String, Object> metaMap = objectMapper.convertValue(e.meta, Map.class);
                    event.setMeta(metaMap);
                }

                eventRepository.save(event);
            }

            System.out.println("Seed data inserted successfully.");
        }
    }
}
