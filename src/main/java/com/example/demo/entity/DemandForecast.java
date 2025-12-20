package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "demand_forecast")
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

    // Used by InventoryBalancerService + tests
    public int getForecastQuantity() {
        return this.forecastedDemand;
    }

    public void setForecastQuantity(int qty) {
        this.forecastedDemand = qty;
    }

    public void setForecastDate(LocalDate date) {
        this.forecastDate = date;
    }

    // =========================
    // 🔥 REQUIRED BY SERVICES
    // =========================

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

    public Long getId() {
        return id;
    }
}
