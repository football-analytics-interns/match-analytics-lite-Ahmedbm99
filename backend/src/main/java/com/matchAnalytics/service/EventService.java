package com.matchAnalytics.service;

import com.matchAnalytics.model.Event;
import com.matchAnalytics.model.Player;
import com.matchAnalytics.repository.EventRepository;
import com.matchAnalytics.repository.PlayerRepository;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class EventService {
    private final EventRepository eventRepository;
    private final PlayerRepository playerRepository;

    public EventService(EventRepository eventRepository, PlayerRepository playerRepository) {
        this.eventRepository = eventRepository;
        this.playerRepository = playerRepository;
    }


    public Event addEvent(Event event) {
        return eventRepository.save(event);
    }

public List<Event> getAllEvents() {
    List<Event> events = eventRepository.findAll();
    for (Event e : events) {
        e.getAssist(playerRepository);
    }
    return events;
}
}