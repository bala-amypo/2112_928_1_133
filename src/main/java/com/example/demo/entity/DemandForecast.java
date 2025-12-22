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
    private int predictedDemand;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Store getStore() { return store; }
    public void setStore(Store store) { this.store = store; }

    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }

    public LocalDate getForecastDate() { return forecastDate; }
    public void setForecastDate(LocalDate forecastDate) { this.forecastDate = forecastDate; }

    public int getPredictedDemand() { return predictedDemand; }
    public void setPredictedDemand(int predictedDemand) { this.predictedDemand = predictedDemand; }

    /* TEST ALIASES */
    public void setForecastedDemand(int demand) {
        this.predictedDemand = demand;
    }

    public int getForecastedDemand() {
        return predictedDemand;
    }
}
