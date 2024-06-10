package org.neeq.is_projekt.service;

import lombok.RequiredArgsConstructor;

import org.neeq.is_projekt.model.Event;
import org.neeq.is_projekt.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EventService {
    private final EventRepository eventRepository;





    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }
}