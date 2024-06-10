package org.neeq.is_projekt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.neeq.is_projekt.model.CoinData;
import org.neeq.is_projekt.model.Event;
import org.neeq.is_projekt.model.HistoricalPrice;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import org.neeq.is_projekt.model.Event;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FetchData {

	@Bean
	static RestTemplate restTemplate() {
		return new RestTemplate();
	}

	public static List<CoinData> fetchCoinData(int days) throws JsonProcessingException {
		String MARKET_CHART_URL = "/coins/{coinId}/market_chart?vs_currency=usd&days={days}";
		List<CoinData> coinDataList = new ArrayList<>();

		String baseUrl = "https://api.coingecko.com/api/v3";

		JsonNode[] coinList = new JsonNode[] {
				new ObjectMapper().readTree("{\"id\": \"bitcoin\", \"name\": \"Bitcoin\"}"),
				new ObjectMapper().readTree("{\"id\": \"ethereum\", \"name\": \"Ethereum\"}"),
				new ObjectMapper().readTree("{\"id\": \"dogecoin\", \"name\": \"Dogecoin\"}"),
				new ObjectMapper().readTree("{\"id\": \"tether\", \"name\": \"Tether\"}"),
				new ObjectMapper().readTree("{\"id\": \"solana\", \"name\": \"Solana\"}"),
		};

		for (JsonNode coin : coinList) {
			String coinId = coin.get("id").asText();
			String coinName = coin.get("name").asText();
			String marketChartUrl = baseUrl + MARKET_CHART_URL.replace("{coinId}", coinId).replace("{days}", String.valueOf(days));
			ResponseEntity<JsonNode> marketChartResponse = restTemplate().getForEntity(marketChartUrl, JsonNode.class);
			JsonNode marketChartData = marketChartResponse.getBody();

			if (marketChartData != null) {
				JsonNode pricesNode = marketChartData.get("prices");
				List<HistoricalPrice> historicalPrices = new ArrayList<>();

				if (pricesNode != null) {
					for (JsonNode priceData : pricesNode) {
						long timestamp = priceData.get(0).asLong();
						Instant instant = Instant.ofEpochSecond(timestamp/1000);
						LocalDateTime date = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
						//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
						double price = priceData.get(1).asDouble();
						historicalPrices.add(new HistoricalPrice(price, date));
					}
				}

				CoinData coinData = new CoinData(coinId, coinName, historicalPrices);
				coinDataList.add(coinData);


				for (HistoricalPrice historicalPrice : historicalPrices) {
					historicalPrice.setCoinData(coinData);
				}
			}
		}

		return coinDataList;
	}

	public static List<Event> fetchEventData() {
		String EVENT_DATA_URL = "https://newsapi.org/v2/everything?q=Crypto&from=2024-05-12&sortBy=popularity&apiKey=e886f6608bfd4f5a95c6f38f141d8dc4";
		ResponseEntity<JsonNode> response = restTemplate().getForEntity(EVENT_DATA_URL, JsonNode.class);
		JsonNode articlesNode = Objects.requireNonNull(response.getBody()).get("articles");

		List<org.neeq.is_projekt.model.Event> events = new ArrayList<>();
		for (JsonNode articleNode : articlesNode) {
			org.neeq.is_projekt.model.Event event = Event.builder()
					.id(articleNode.get("source").get("name").asText())
					.author(articleNode.get("author").asText())
					.title(articleNode.get("title").asText())
					.description(articleNode.get("description").asText())
					.url(articleNode.get("url").asText())
					.publishedAt(LocalDateTime.parse(articleNode.get("publishedAt").asText(), DateTimeFormatter.ISO_DATE_TIME))
					.content(articleNode.get("content").asText())
					.build();
			events.add(event);
		}

		return events;
	}
}
