package org.neeq.is_projekt.service;

import lombok.AllArgsConstructor;
import org.neeq.is_projekt.dto.CoinDataDTO;
import org.neeq.is_projekt.dto.HistoricalPriceDTO;
import org.neeq.is_projekt.model.CoinData;
import org.neeq.is_projekt.repository.CoinDataRepository;
import org.neeq.is_projekt.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CoinDataService {
    private final CoinDataRepository coinDataRepository;
    private final EventService eventService;
    private final EventRepository eventRepository;


    public List<CoinDataDTO> getAllCoinData() {
        List<CoinData> coinData = coinDataRepository.findAll();
        return coinData.stream()
                .map(this::mapToCoinDataDTO)
                .collect(Collectors.toList());
    }

    private CoinDataDTO mapToCoinDataDTO(CoinData coinData) {
        return new CoinDataDTO(
                coinData.getId(),
                coinData.getName(),
                coinData.getHistoricalPrices().stream()
                        .map(historicalPrice -> new HistoricalPriceDTO(
                                historicalPrice.getPrice(),
                                historicalPrice.getTimestamp(),
                                eventRepository.findAllByPublishedAtBetween(
                                        historicalPrice.getTimestamp().toLocalDate().atStartOfDay(),
                                        historicalPrice.getTimestamp().toLocalDate().atTime(LocalTime.MAX)))
                        )
                        .collect(Collectors.toList())
        );
    }

    public CoinDataDTO getCoinDataById(String id) {
        CoinData coinData = coinDataRepository.findById(id).orElseThrow();
        return mapToCoinDataDTO(coinData);
    }
}
