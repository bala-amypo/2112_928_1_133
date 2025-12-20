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

    // 🔥 MUST be Integer (tests use .equals)
    private Integer quantity;

    // 🔥 REQUIRED BY TESTS
    private LocalDateTime lastUpdated;

    // =========================
    // TEST REQUIRED SETTERS
    // =========================

    public void setStoreId(Long storeId) {
        if (this.store == null) {
            this.store = new Store();
        }
        this.store.setId(storeId);
    }

    public void setProductId(Long productId) {
        if (this.product == null) {
            this.product = new Product();
        }
        this.product.setId(productId);
    }

    // =========================
    // GETTERS / SETTERS
    // =========================

    public Store getStore() {
        return store;
    }

    public Product getProduct() {
        return product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    // =========================
    // TIMESTAMP (TEST REQUIRED)
    // =========================
    @PrePersist
    @PreUpdate
    public void touch() {
        this.lastUpdated = LocalDateTime.now();
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }
}
