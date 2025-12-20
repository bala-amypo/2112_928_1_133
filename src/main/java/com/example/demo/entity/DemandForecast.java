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

    private int forecastedDemand;

    private LocalDate forecastDate;

    // =======================
    // REQUIRED BY TESTS
    // =======================

    public Long getId() {
        return id;
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

    public int getForecastedDemand() {
        return forecastedDemand;
    }

    public void setForecastedDemand(int forecastedDemand) {
        this.forecastedDemand = forecastedDemand;
    }

    // Alias used by SERVICE + TEST
    public int getForecastQuantity() {
        return forecastedDemand;
    }

    public void setForecastQuantity(int qty) {
        this.forecastedDemand = qty;
    }

    public LocalDate getForecastDate() {
        return forecastDate;
    }

    public void setForecastDate(LocalDate forecastDate) {
        this.forecastDate = forecastDate;
    }
}
