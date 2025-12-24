package com.example.demo.entity;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
public class TransferSuggestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer suggestedQuantity;
    private String reason;
    private Timestamp generatedAt;

    @PrePersist
    public void prePersist() {
        generatedAt = new Timestamp(System.currentTimeMillis());
    }

    public Long getId() {
        return id;
    }

    public Integer getSuggestedQuantity() {
        return suggestedQuantity;
    }

    public String getReason() {
        return reason;
    }

    public Timestamp getGeneratedAt() {
        return generatedAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSuggestedQuantity(Integer suggestedQuantity) {
        this.suggestedQuantity = suggestedQuantity;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
