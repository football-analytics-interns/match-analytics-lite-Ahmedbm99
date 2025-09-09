package com.matchAnalytics.model;

import jakarta.persistence.*;
import java.util.Map;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "events")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int minute;
    private String type;

    @Column(name = "player_id", insertable = false, updatable = false)
    private Long playerId;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    @ManyToOne
    @JoinColumn(name = "match_id")
    @JsonBackReference("match-events")
    private Match match;

    @Column(columnDefinition = "json")
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> meta;

    @Transient
    private Player assist;

    public Event() {}

    public Event(int minute, String type, Long playerId, Player player, Map<String, Object> meta) {
        this.minute = minute;
        this.type = type;
        this.playerId = playerId;
        this.player = player;
        this.meta = meta;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public int getMinute() { return minute; }
    public void setMinute(int minute) { this.minute = minute; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public Long getPlayerId() { return playerId; }
    public void setPlayerId(Long playerId) { this.playerId = playerId; }

    public Player getPlayer() { return player; }
    public void setPlayer(Player player) { this.player = player; }

    public Match getMatch() { return match; }
    public void setMatch(Match match) { this.match = match; }

    public Map<String, Object> getMeta() { return meta; }
    public void setMeta(Map<String, Object> meta) { this.meta = meta; }

    public Player getAssist() { return assist; }
    public void setAssist(Player assist) { this.assist = assist; }
}
