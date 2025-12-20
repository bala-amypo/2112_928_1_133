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

    // =========================
    // 🔥 REQUIRED BY TESTS
    // =========================

    public Long getId() {
        return id;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Store getStore() {
        return store;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    // 🔥 TEST + SERVICE REQUIRED
    public int getForecastQuantity() {
        return this.forecastedDemand;
    }

    public void setForecastQuantity(int qty) {
        this.forecastedDemand = qty;
    }

    // 🔥 TEST REQUIRED
    public void setForecastedDemand(int demand) {
        this.forecastedDemand = demand;
    }

    public int getForecastedDemand() {
        return forecastedDemand;
    }

    // 🔥 TEST REQUIRED
    public void setForecastDate(LocalDate date) {
        this.forecastDate = date;
    }

    public LocalDate getForecastDate() {
        return forecastDate;
    }
}
