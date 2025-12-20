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

    private int quantity;                 // used by service
    private int suggestedQuantity;         // used by tests
    private String reason;                 // used by tests
    private String priority;               // used by service
    private LocalDateTime generatedAt;     // used by service

    // ===== REQUIRED SETTERS / GETTERS =====

    public void setSourceStore(Store sourceStore) {
        this.sourceStore = sourceStore;
    }

    public Store getSourceStore() {
        return sourceStore;
    }

    public void setTargetStore(Store targetStore) {
        this.targetStore = targetStore;
    }

    public Store getTargetStore() {
        return targetStore;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    // 🔥 REQUIRED BY SERVICE
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    // 🔥 REQUIRED BY TEST
    public void setSuggestedQuantity(int qty) {
        this.suggestedQuantity = qty;
    }

    public int getSuggestedQuantity() {
        return suggestedQuantity;
    }

    // 🔥 REQUIRED BY TEST
    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    // 🔥 REQUIRED BY SERVICE
    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getPriority() {
        return priority;
    }

    // 🔥 REQUIRED BY SERVICE
    public void setGeneratedAt(LocalDateTime generatedAt) {
        this.generatedAt = generatedAt;
    }

    public LocalDateTime getGeneratedAt() {
        return generatedAt;
    }
}
