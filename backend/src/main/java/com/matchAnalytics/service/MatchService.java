package com.matchAnalytics.service;
import com.matchAnalytics.model.*;
import com.matchAnalytics.repository.EventRepository;
import com.matchAnalytics.repository.MatchRepository;
import com.matchAnalytics.repository.PlayerRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatchService {

    private final MatchRepository matchRepository;
    private final PlayerRepository playerRepository;
    private final EventRepository eventRepository;

    public MatchService(MatchRepository matchRepository,
                        PlayerRepository playerRepository,
                        EventRepository eventRepository) {
        this.matchRepository = matchRepository;
        this.playerRepository = playerRepository;
        this.eventRepository = eventRepository;
    }

    public Optional<Match> getMatch(Long id) {
        return matchRepository.findById(id).map(match -> {
            // Fetch players for the match
            List<Player> players = playerRepository.findByMatchId(match.getId());

            // Fetch events for the match
            List<Event> events = eventRepository.findByMatchId(match.getId());

            // Enrich events with player info
            List<Event> eventDTOs = events.stream().map(event -> {
                Event dto = new Event();
                dto.setMinute(event.getMinute());
                dto.setType(event.getType());

                // Set player info
                Player player = players.stream()
                        .filter(p -> p.getId().equals(event.getPlayerId()))
                        .findFirst()
                        .orElse(null);
                dto.setPlayer(player);

                // Set assist info if present
                if (event.getMeta() != null && event.getAssist(playerRepository) != null) {
                    Player assistPlayer = players.stream()
                            .filter(p -> p.getId().equals(event.getAssist(playerRepository)))
                            .findFirst()
                            .orElse(null);
                    dto.setAssist(assistPlayer);
                }

                // Add other meta if needed
                dto.setMeta(event.getMeta());

                return dto;
            }).toList();

            // Build MatchDTO
            Match matchDTO = new Match();
            matchDTO.setId(match.getId());
            matchDTO.setDate(match.getDate());
            matchDTO.setHomeTeam(match.getHomeTeam());
            matchDTO.setAwayTeam(match.getAwayTeam());
            matchDTO.setHomeScore(match.getHomeScore());
            matchDTO.setAwayScore(match.getAwayScore());
            matchDTO.setPlayers(players);
            matchDTO.setEvents(eventDTOs);

            return matchDTO;
        });
    }
}
