package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class DemandForecast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔥 REQUIRED — fixes "No property store found"
    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private int forecastedDemand;

    // ========= GETTERS =========

    public Long getId() {
        return id;
    }

    public Store getStore() {
        return store;
    }

    public Product getProduct() {
        return product;
    }

    public int getForecastedDemand() {
        return forecastedDemand;
    }

    // 🔥 REQUIRED BY InventoryBalancerServiceImpl
    public int getForecastQuantity() {
        return forecastedDemand;
    }

    // ========= SETTERS =========

    public void setId(Long id) {
        this.id = id;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setForecastedDemand(int forecastedDemand) {
        this.forecastedDemand = forecastedDemand;
    }

    // 🔥 REQUIRED BY TESTS
    public void setForecastQuantity(int quantity) {
        this.forecastedDemand = quantity;
    }
}
