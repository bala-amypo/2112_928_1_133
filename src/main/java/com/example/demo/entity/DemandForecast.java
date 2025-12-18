package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "demand_forecast")
public class DemandForecast {

    @Id
    private Long productId;

    private int forecastQuantity;

    public DemandForecast() {
    }

    public DemandForecast(Long productId, int forecastQuantity) {
        this.productId = productId;
        this.forecastQuantity = forecastQuantity;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getForecastQuantity() {
        return forecastQuantity;
    }

    public void setForecastQuantity(int forecastQuantity) {
        this.forecastQuantity = forecastQuantity;
    }
}
