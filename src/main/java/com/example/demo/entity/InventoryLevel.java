package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class InventoryLevel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Store store;

    @ManyToOne
    private Product product;

    private Integer quantity;

    private LocalDateTime lastUpdated;

    public Store getStore() {
        return store;
    }

    public InventoryLevel setStore(Store store) {
        this.store = store;
        return this;
    }

    public Product getProduct() {
        return product;
    }

    public InventoryLevel setProduct(Product product) {
        this.product = product;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public InventoryLevel setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    @PrePersist
    @PreUpdate
    public void touch() {
        this.lastUpdated = LocalDateTime.now();
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }
}
