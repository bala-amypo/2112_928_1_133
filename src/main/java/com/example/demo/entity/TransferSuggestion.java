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

    private String reason;

    private LocalDateTime generatedAt;

    // =========================
    // ✅ GETTERS
    // =========================

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

    public String getPriority() {
        return priority;
    }

    public String getReason() {
        return reason;
    }

    public LocalDateTime getGeneratedAt() {
        return generatedAt;
    }

    // =========================
    // 🔥 REQUIRED BY TESTS + SERVICES
    // =========================

    public void setQuantity(int qty) {
        this.quantity = qty;
    }

    // tests call this instead of setQuantity
    public void setSuggestedQuantity(int qty) {
        this.quantity = qty;
    }

    public void setSourceStore(Store store) {
        this.sourceStore = store;
    }

    public void setTargetStore(Store store) {
        this.targetStore = store;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setGeneratedAt(LocalDateTime generatedAt) {
        this.generatedAt = generatedAt;
    }
}
