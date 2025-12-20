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
    // 🔥 REQUIRED BY TESTS
    // =========================

    public void setQuantity(int qty) {
        this.quantity = qty;
    }

    public void setSuggestedQuantity(int qty) {
        this.quantity = qty;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getPriority() {
        return priority;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setGeneratedAt(LocalDateTime generatedAt) {
        this.generatedAt = generatedAt;
    }
}
