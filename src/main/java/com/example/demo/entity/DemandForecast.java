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

    // ---------- TEST + SERVICE REQUIRED ----------

    public int getForecastQuantity() {
        return forecastedDemand;
    }

    public void setForecastQuantity(int qty) {
        this.forecastedDemand = qty;
    }

    // 🔥 TEST REQUIRED
    public void setForecastDate(LocalDate date) {
        this.forecastDate = date;
    }

    public LocalDate getForecastDate() {
        return forecastDate;
    }

    public Store getStore() {
        return store;
    }

    public Product getProduct() {
        return product;
    }
}
