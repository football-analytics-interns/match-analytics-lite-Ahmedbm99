package com.matchAnalytics.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;

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

    @Column(columnDefinition = "jsonb")
    private String meta; 

    @Transient
    private JsonNode metaObject;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }

    public JsonNode getMetaObject() {
        return metaObject;
    }

    public void setMetaObject(JsonNode metaObject) {
        this.metaObject = metaObject;
    }

    public Event(int minute, String type, Long playerId, Player player, String meta, JsonNode metaObject) {
        this.minute = minute;
        this.type = type;
        this.playerId = playerId;
        this.player = player;
        this.meta = meta;
        this.metaObject = metaObject;
    }

    public Event() {}

    
}
