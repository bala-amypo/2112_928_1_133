package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class TransferSuggestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Store sourceStore;

    @ManyToOne
    private Store targetStore;

    @ManyToOne
    private Product product;

    // 🔥 REQUIRED BY SERVICE & TESTS
    private int quantity;

    // 🔥 REQUIRED BY TESTS
    private String reason;

    // 🔥 REQUIRED BY TESTS
    private LocalDateTime generatedAt;

    /* ===================== GETTERS ===================== */

    public Long getId() {
        return id;
    }

    public Store getSourceStore() {
        return sourceStore;
    }

    public Store getTargetStore() {
        return targetStore;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getReason() {
        return reason;
    }

    public LocalDateTime getGeneratedAt() {
        return generatedAt;
    }

    /* ===================== SETTERS ===================== */

    public void setSourceStore(Store sourceStore) {
        this.sourceStore = sourceStore;
    }

    public void setTargetStore(Store targetStore) {
        this.targetStore = targetStore;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    // 🔥 THIS FIXES YOUR ERROR
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setGeneratedAt(LocalDateTime generatedAt) {
        this.generatedAt = generatedAt;
    }

    /* ===================== JPA CALLBACK ===================== */

    @PrePersist
    public void onCreate() {
        this.generatedAt = LocalDateTime.now();
    }
}
