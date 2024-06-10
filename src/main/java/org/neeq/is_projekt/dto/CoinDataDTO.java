package org.neeq.is_projekt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoinDataDTO {
    private String id;
    private String name;
    private List<HistoricalPriceDTO> historicalPrices;
}