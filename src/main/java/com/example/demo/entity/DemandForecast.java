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

    // ---------- getters ----------
    public Long getId() {
        return id;
    }

    public Store getStore() {
        return store;
    }

    public Product getProduct() {
        return product;
    }

    public int getForecastQuantity() {
        return forecastedDemand;
    }

    public LocalDate getForecastDate() {
        return forecastDate;
    }

    // ---------- setters (TEST REQUIRED) ----------
    public void setStore(Store store) {
        this.store = store;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setForecastedDemand(int qty) {
        this.forecastedDemand = qty;
    }

    public void setForecastQuantity(int qty) {
        this.forecastedDemand = qty;
    }

    public void setForecastDate(LocalDate date) {
        this.forecastDate = date;
    }
}
