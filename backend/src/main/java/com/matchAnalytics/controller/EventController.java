package com.matchAnalytics.controller;

import com.matchAnalytics.model.Event;
import com.matchAnalytics.repository.PlayerRepository;
import com.matchAnalytics.service.EventService;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/event")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public ResponseEntity<Event> addEvent(@RequestBody Event event) {
        return ResponseEntity.ok(eventService.addEvent(event));
    }


}
