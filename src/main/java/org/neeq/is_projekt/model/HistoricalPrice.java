package org.neeq.is_projekt.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "historical_prices")
public class HistoricalPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "price")
    private Double price;

    @Column(name = "timestamp")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "coin_data_id", referencedColumnName = "id")
    private CoinData coinData;



    public HistoricalPrice(Double price, LocalDateTime timestamp) {
        this.price = price;
        this.timestamp = timestamp;
    }

}
