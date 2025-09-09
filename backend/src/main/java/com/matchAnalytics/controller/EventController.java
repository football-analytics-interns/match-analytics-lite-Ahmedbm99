package com.matchAnalytics.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.matchAnalytics.dto.SeedData.EventDTO;
import com.matchAnalytics.model.Event;
import com.matchAnalytics.model.Match;
import com.matchAnalytics.model.Player;
import com.matchAnalytics.repository.MatchRepository;
import com.matchAnalytics.repository.PlayerRepository;
import com.matchAnalytics.service.EventService;

import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/event")
public class EventController {

    private final PlayerRepository playerRepository;
    private final MatchRepository matchRepository;
    private final EventService eventService;

    public EventController(EventService eventService, PlayerRepository playerRepository, MatchRepository matchRepository) {
        this.eventService = eventService;
        this.playerRepository = playerRepository;
        this.matchRepository = matchRepository;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Event> addEvent(@RequestBody EventDTO dto) {


        System.out.println("Received EventDTO: " + dto);
        Event event = new Event();
        event.setType(dto.type);
        event.setMinute(dto.minute);
        Match match = matchRepository.findById(dto.matchId).orElse(null);
        event.setMatch(match);
        playerRepository.findById(dto.playerId).ifPresent(event::setPlayer);
        event.setPlayerId(dto.playerId);
        Player player = playerRepository.findById(dto.playerId).orElse(null);
        event.setPlayer(player);
        if (dto.meta != null) {
            ObjectMapper mapper = new ObjectMapper();
            Map<String,Object> metaMap = mapper.convertValue(dto.meta, Map.class);
            event.setMeta(metaMap);
        }       
        Event saved = eventService.addEvent(event);

        return ResponseEntity.ok(saved);
    }
}
