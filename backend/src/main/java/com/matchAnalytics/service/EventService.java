package com.matchAnalytics.service;

import com.matchAnalytics.model.Event;
import com.matchAnalytics.model.Player;
import com.matchAnalytics.repository.EventRepository;
import com.matchAnalytics.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final PlayerRepository playerRepository;

    public EventService(EventRepository eventRepository, PlayerRepository playerRepository) {
        this.eventRepository = eventRepository;
        this.playerRepository = playerRepository;
    }

    /**
     * Save a new event.
     */
    public Event addEvent(Event event) {
        // If meta contains assistId, populate transient assist field
        if (event.getMeta() != null && event.getMeta().containsKey("assistId")) {
            Object assistIdObj = event.getMeta().get("assistId");
            if (assistIdObj != null) {
                Long assistId = Long.valueOf(assistIdObj.toString());
                Player assistPlayer = playerRepository.findById(assistId).orElse(null);
                event.setAssist(assistPlayer);
            }
        }
        return eventRepository.save(event);
    }

    /**
     * Retrieve all events and resolve transient assist field.
     */
    public List<Event> getAllEvents() {
        List<Event> events = eventRepository.findAll();
        for (Event e : events) {
            Map<String, Object> meta = e.getMeta();
            if (meta != null && meta.containsKey("assistId")) {
                Object assistIdObj = meta.get("assistId");
                if (assistIdObj != null) {
                    Long assistId = Long.valueOf(assistIdObj.toString());
                    Player assistPlayer = playerRepository.findById(assistId).orElse(null);
                    e.setAssist(assistPlayer);
                }
            }
        }
        return events;
    }
}
