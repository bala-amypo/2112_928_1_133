package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class TransferSuggestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;
    private String reason;

    private LocalDateTime generatedAt = LocalDateTime.now();

    public TransferSuggestion() {}

    public Long getId() {
        return id;
    }

    // REQUIRED
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

    // 🔥 REQUIRED BY TEST
    public LocalDateTime getGeneratedAt() {
        return generatedAt;
    }
}
