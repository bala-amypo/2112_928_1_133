package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class DemandForecast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int predictedDemand;
    private LocalDate forecastDate;

    @ManyToOne
    private Store store;

    @ManyToOne
    private Product product;

    public int getPredictedDemand() {
        return predictedDemand;
    }

    public void setPredictedDemand(int predictedDemand) {
        this.predictedDemand = predictedDemand;
    }

    public LocalDate getForecastDate() {
        return forecastDate;
    }

    public Store getStore() {
        return store;
    }

    public Product getProduct() {
        return product;
    }
}
