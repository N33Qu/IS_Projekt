package org.neeq.is_projekt.web;

import lombok.AllArgsConstructor;
import org.neeq.is_projekt.dto.CoinDataDTO;
import org.neeq.is_projekt.service.CoinDataService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/coins")
public class CoinDataController {
    private final CoinDataService coinDataService;

    @GetMapping
    public List<CoinDataDTO> getAllCoinData() {
        return coinDataService.getAllCoinData();
    }

    @GetMapping("{id}")
    public CoinDataDTO getCoinDataById(@PathVariable String id) {
        return coinDataService.getCoinDataById(id);
    }



}
