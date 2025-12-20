package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class DemandForecast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int forecastedDemand;

    private LocalDate forecastDate;

    // ✅ REQUIRED BY TEST
    public int getForecastQuantity() {
        return forecastedDemand;
    }

    public void setForecastQuantity(int qty) {
        this.forecastedDemand = qty;
    }

    // ✅ REQUIRED BY TEST
    public int getForecastedDemand() {
        return forecastedDemand;
    }

    public void setForecastedDemand(int forecastedDemand) {
        this.forecastedDemand = forecastedDemand;
    }

    // ✅ REQUIRED BY TEST
    public LocalDate getForecastDate() {
        return forecastDate;
    }

    public void setForecastDate(LocalDate forecastDate) {
        this.forecastDate = forecastDate;
    }
}
