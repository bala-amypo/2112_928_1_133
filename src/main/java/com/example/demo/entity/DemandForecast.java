package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class DemandForecast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Store store;

    @ManyToOne
    private Product product;

    private Integer forecastQuantity;

    public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
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
 
    public Integer getForecastQuantity() {
        return forecastQuantity;
    }
 
    public void setForecastQuantity(Integer forecastQuantity) {
        this.forecastQuantity = forecastQuantity;
    }
}
