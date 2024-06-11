package org.neeq.is_projekt.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank; // Import the validation constraint
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "coin_data")
public class CoinData {
    @Id
    @Column(name = "id")
    @NotBlank(message = "Coin ID cannot be blank")
    @Size(max = 50, message = "Coin ID cannot be longer than 50 characters")
    private String id;

    @Column(name = "name")
    @NotBlank(message = "Coin name cannot be blank")
    @Size(max = 100, message = "Coin name cannot be longer than 100 characters")
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "coinData", cascade = CascadeType.ALL)
    private List<HistoricalPrice> historicalPrices;
}