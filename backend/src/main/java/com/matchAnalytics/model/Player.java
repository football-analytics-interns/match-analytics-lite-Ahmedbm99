package com.matchAnalytics.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "players")
public class Player {
    @Id
    private Long id;

    private String name;
    private String team;
    private String position;

    @ManyToOne
    @JoinColumn(name = "match_id")
    private Match match;

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Event> events;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public Player() {}

    public Player(String name, String team, String position, Match match, List<Event> events) {
        this.name = name;
        this.team = team;
        this.position = position;
        this.match = match;
        this.events = events;
    }


    
}
