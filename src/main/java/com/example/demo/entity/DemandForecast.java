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

    // main field used by DB
    private int forecastedDemand;

    private LocalDate forecastDate;

    // =========================
    // 🔥 GETTERS & SETTERS
    // =========================

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

    public LocalDate getForecastDate() {
        return forecastDate;
    }

    // =========================
    // 🔥 TEST + SERVICE ALIASES
    // (DO NOT REMOVE)
    // =========================

    // ✔ used by InventoryBalancerService + tests
    public int getForecastQuantity() {
        return this.forecastedDemand;
    }

    public void setForecastQuantity(int qty) {
        this.forecastedDemand = qty;
    }

    // ✔ used by MultiLocationInventoryBalancerTest
    public void setForecastDate(LocalDate date) {
        this.forecastDate = date;
    }
}
