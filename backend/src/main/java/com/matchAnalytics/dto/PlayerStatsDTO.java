
package com.matchAnalytics.dto;

public class PlayerStatsDTO {
    private Long playerId;
    private String playerName;
    private int goals;
    private int assists;
    private double rating;

    public PlayerStatsDTO(Long playerId, String playerName, int goals, int assists, double rating) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.goals = goals;
        this.assists = assists;
        this.rating = rating;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public PlayerStatsDTO() { }

}
