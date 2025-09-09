package com.matchAnalytics.dto;

import com.fasterxml.jackson.databind.JsonNode;
import com.matchAnalytics.model.Match;
import com.matchAnalytics.model.Player;

import java.util.List;

public class SeedData {
    public MatchDTO match;
    public List<PlayerDTO> players;
    public List<EventDTO> events;

    public static class MatchDTO {
        public Long id;
        public String date;
        public String homeTeam;
        public String awayTeam;
        public int homeScore;
        public int awayScore;
    }

    public static class PlayerDTO {
        public Long id;
        public String name;
        public String team;
        public String position;
    }

    public static class EventDTO {
        public int minute;
        public String type;
        public Long playerId;
        public JsonNode meta; 
        public Long matchId;
    }
}
