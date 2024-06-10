package org.neeq.is_projekt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neeq.is_projekt.model.Event;

import java.time.LocalDateTime;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoricalPriceDTO {
    private double price;
    private LocalDateTime timestamp;
    private List<Event> events;
}