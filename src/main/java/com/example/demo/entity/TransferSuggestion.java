package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class TransferSuggestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int suggestedQuantity;

    private String reason;

    private String priority;

    private LocalDateTime generatedAt = LocalDateTime.now();

    @ManyToOne
    private Store sourceStore;

    @ManyToOne
    private Store targetStore;

    @ManyToOne
    private Product product;

    public TransferSuggestion() {}

    public Long getId() {
        return id;
    }

    public int getSuggestedQuantity() {
        return suggestedQuantity;
    }

    public void setSuggestedQuantity(int suggestedQuantity) {
        this.suggestedQuantity = suggestedQuantity;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
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
}
