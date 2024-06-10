package org.neeq.is_projekt.repository;

import org.neeq.is_projekt.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;


public interface EventRepository extends JpaRepository<Event, String> {
    List<Event> findAllByPublishedAtBetween(LocalDateTime startDateTime, LocalDateTime endDateTime);
}