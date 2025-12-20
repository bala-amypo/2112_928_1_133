package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class DemandForecast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int forecastedDemand;

    @ManyToOne
    private Store store;

    @ManyToOne
    private Product product;

    public DemandForecast() {}

    public Long getId() {
        return id;
    }

    public int getForecastedDemand() {
        return forecastedDemand;
    }

    // 🔥 REQUIRED BY InventoryBalancerServiceImpl
    public int getForecastQuantity() {
        return forecastedDemand;
    }

    public void setForecastedDemand(int forecastedDemand) {
        this.forecastedDemand = forecastedDemand;
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
}
