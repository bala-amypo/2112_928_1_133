package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class DemandForecast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Store store;

    @ManyToOne
    private Product product;

    private LocalDate forecastDate;

    private Integer forecastedDemand;

    private Double confidenceScore;

    // -------- Getters & Setters --------

    public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }

    public Store getStore() {
        return store;
    }
 
    public void setStore(Store store) {
        this.store = store;
    }

    public Product getProduct() {
        return product;
    }
 
    public void setProduct(Product product) {
        this.product = product;
    }

    public LocalDate getForecastDate() {
        return forecastDate;
    }
 
    public void setForecastDate(LocalDate forecastDate) {
        this.forecastDate = forecastDate;
    }

    public Integer getForecastedDemand() {
        return forecastedDemand;
    }

    public void setForecastedDemand(Integer forecastedDemand) {
        this.forecastedDemand = forecastedDemand;
    }

    // ⭐ REQUIRED ALIAS FOR TESTS
    public Integer getPredictedDemand() {
        return forecastedDemand;
    }

    public Double getConfidenceScore() {
        return confidenceScore;
    }

    public void setConfidenceScore(Double confidenceScore) {
        this.confidenceScore = confidenceScore;
    }
}
