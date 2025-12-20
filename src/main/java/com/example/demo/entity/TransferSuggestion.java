package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class TransferSuggestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;

    private int suggestedQuantity;

    private String reason;

    private String priority;

    private LocalDateTime generatedAt;

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
