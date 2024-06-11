package org.neeq.is_projekt.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull; // Import the validation constraint
import jakarta.validation.constraints.Positive; // Import the validation constraint
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
    @NotNull(message = "Price cannot be null")
    @Positive(message = "Price must be a positive value")
    private Double price;

    @Column(name = "timestamp")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Timestamp cannot be null")
    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "coin_data_id", referencedColumnName = "id")
    @NotNull(message = "CoinData cannot be null")
    private CoinData coinData;

    public HistoricalPrice(Double price, LocalDateTime timestamp) {
        this.price = price;
        this.timestamp = timestamp;
    }
}