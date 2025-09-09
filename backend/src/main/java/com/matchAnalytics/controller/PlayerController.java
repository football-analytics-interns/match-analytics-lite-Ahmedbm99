package com.matchAnalytics.controller;

import com.matchAnalytics.dto.PlayerStatsDTO;
import com.matchAnalytics.service.PlayerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/player")
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerStatsDTO> getPlayerStats(@PathVariable Long id) {
        return ResponseEntity.ok(playerService.getPlayerStats(id));
    }
}
