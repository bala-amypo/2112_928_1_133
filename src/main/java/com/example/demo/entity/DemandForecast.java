package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class DemandForecast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;
    private int forecastQuantity;

    public DemandForecast() {}

    public DemandForecast(Long productId, int forecastQuantity) {
        this.productId = productId;
        this.forecastQuantity = forecastQuantity;
    }

    public Long getId() {
        return id;
    }

    public Long getProductId() {
        return productId;
    }

    public int getForecastQuantity() {
        return forecastQuantity;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setForecastQuantity(int forecastQuantity) {
        this.forecastQuantity = forecastQuantity;
    }
}
