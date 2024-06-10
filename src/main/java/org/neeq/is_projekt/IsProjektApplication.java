package org.neeq.is_projekt;

import lombok.AllArgsConstructor;
import org.neeq.is_projekt.repository.CoinDataRepository;
import org.neeq.is_projekt.repository.EventRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;


import static org.neeq.is_projekt.FetchData.fetchCoinData;
import static org.neeq.is_projekt.FetchData.fetchEventData;

@AllArgsConstructor
@EnableTransactionManagement
@SpringBootApplication
public class IsProjektApplication implements CommandLineRunner{

	CoinDataRepository coinDataRepository;
	EventRepository eventRepository;


	public static void main(String[] args) {
		
		SpringApplication.run(IsProjektApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		coinDataRepository.saveAll(fetchCoinData(30));
		eventRepository.saveAll(fetchEventData());
	}

}
