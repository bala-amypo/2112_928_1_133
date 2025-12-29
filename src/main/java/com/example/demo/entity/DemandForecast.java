package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "demand_forecasts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DemandForecast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Store store;

    @ManyToOne
    private Product product;

    @Column(nullable = false)
    private Integer forecastedDemand;

    @Column(nullable = false)
    private LocalDate forecastDate;

    private Integer confidenceScore;
}
