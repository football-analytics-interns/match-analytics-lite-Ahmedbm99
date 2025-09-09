package com.matchAnalytics.controller;

import com.matchAnalytics.demo.MatchAnalyticsApplication;
import com.matchAnalytics.model.Match;
import com.matchAnalytics.service.MatchService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = MatchAnalyticsApplication.class)
@AutoConfigureMockMvc
class MatchControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MatchService matchService;

    @Test
    void shouldReturnMatch() throws Exception {
        Match match = new Match();
        match.setId(1L);
        match.setHomeTeam("Blue FC");
        match.setAwayTeam("Red United");
        match.setDate("2025-09-01T18:30:00Z");
        match.setHomeScore(2);
        match.setAwayScore(1);

        when(matchService.getMatch(1L)).thenReturn(Optional.of(match));

        mockMvc.perform(get("/api/match"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.homeTeam").value("Blue FC"))
                .andExpect(jsonPath("$.awayTeam").value("Red United"))
                .andExpect(jsonPath("$.homeScore").value(2))
                .andExpect(jsonPath("$.awayScore").value(1));
    }
}
