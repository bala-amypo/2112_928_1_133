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

    private int quantity;

    private String priority;

    private String reason;   // 🔥 REQUIRED BY TESTS

    private LocalDateTime generatedAt;

    // ---------- GETTERS / SETTERS ----------

    public Long getId() {
        return id;
    }

    public Store getSourceStore() {
        return sourceStore;
    }

    public void setSourceStore(Store sourceStore) {
        this.sourceStore = sourceStore;
    }

    public Store getTargetStore() {
        return targetStore;
    }

    public void setTargetStore(Store targetStore) {
        this.targetStore = targetStore;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    // 🔥 SERVICE REQUIRED
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // 🔥 TEST REQUIRED (alias)
    public void setSuggestedQuantity(int qty) {
        this.quantity = qty;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    // 🔥 NEW (TEST REQUIRED)
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDateTime getGeneratedAt() {
        return generatedAt;
    }

    public void setGeneratedAt(LocalDateTime generatedAt) {
        this.generatedAt = generatedAt;
    }
}
