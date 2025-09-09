package com.matchAnalytics.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.matchAnalytics.demo.MatchAnalyticsApplication;
import com.matchAnalytics.model.Event;
import com.matchAnalytics.service.EventService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest(classes = MatchAnalyticsApplication.class)
@AutoConfigureMockMvc
class EventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventService eventService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateEvent() throws Exception {
        Event event = new Event();
        event.setId(1L);
        event.setMinute(12);
        event.setType("GOAL");
        event.setMeta("{\"assistId\":2}");

        when(eventService.addEvent(org.mockito.ArgumentMatchers.any(Event.class))).thenReturn(event);

        mockMvc.perform(post("/api/event")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(event)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.type").value("GOAL"))
                .andExpect(jsonPath("$.minute").value(12))
                .andExpect(jsonPath("$.meta").value("{\"assistId\":2}"));
    }
}
