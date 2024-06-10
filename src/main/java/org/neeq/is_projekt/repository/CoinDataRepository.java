package org.neeq.is_projekt.repository;

import org.neeq.is_projekt.model.CoinData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoinDataRepository extends JpaRepository<CoinData, String> {
}
