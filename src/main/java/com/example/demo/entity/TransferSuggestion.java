package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class TransferSuggestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔥 REQUIRED BY TESTS
    private int quantity;

    private String reason;

    // 🔥 REQUIRED BY TESTS
    private String priority;

    // 🔥 REQUIRED BY TESTS
    private LocalDateTime generatedAt;

    @ManyToOne
    @JoinColumn(name = "source_store_id")
    private Store sourceStore;

    @ManyToOne
    @JoinColumn(name = "target_store_id")
    private Store targetStore;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    // ===== CONSTRUCTORS =====
    public TransferSuggestion() {
    }

    // ===== GETTERS & SETTERS =====
    public Long getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
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

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public LocalDateTime getGeneratedAt() {
        return generatedAt;
    }

    public void setGeneratedAt(LocalDateTime generatedAt) {
        this.generatedAt = generatedAt;
    }

    // ===== 🔥 TEST-REQUIRED ALIAS METHODS =====

    // Tests expect this name
    public int getSuggestedQuantity() {
        return this.quantity;
    }

    public void setSuggestedQuantity(int qty) {
        this.quantity = qty;
    }
}
