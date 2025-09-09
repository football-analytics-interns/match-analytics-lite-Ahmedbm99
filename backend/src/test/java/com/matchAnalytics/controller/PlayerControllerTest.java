package com.matchAnalytics.controller;

import com.matchAnalytics.demo.MatchAnalyticsApplication;
import com.matchAnalytics.dto.PlayerStatsDTO;
import com.matchAnalytics.service.PlayerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = MatchAnalyticsApplication.class)
@AutoConfigureMockMvc
class PlayerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlayerService playerService;

    @Test
    void shouldReturnPlayerStats() throws Exception {
        PlayerStatsDTO stats = new PlayerStatsDTO(1L, "Ali", 1, 1, 8.5);

        when(playerService.getPlayerStats(1L)).thenReturn(stats);

        mockMvc.perform(get("/api/player/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.playerName").value("Ali"))
                .andExpect(jsonPath("$.goals").value(1))
                .andExpect(jsonPath("$.assists").value(1))
                .andExpect(jsonPath("$.rating").value(8.5));
    }
}
