package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class TransferSuggestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int suggestedQuantity;
    private String reason;

    @ManyToOne
    private Product product;

    public TransferSuggestion() {
    }

    // ===== GETTERS =====

    public Long getId() {
        return id;
    }

    public int getSuggestedQuantity() {
        return suggestedQuantity;
    }

    public String getReason() {
        return reason;
    }

    public Product getProduct() {
        return product;
    }

    // ===== SETTERS =====

    public void setId(Long id) {
        this.id = id;
    }

    public void setSuggestedQuantity(int suggestedQuantity) {
        this.suggestedQuantity = suggestedQuantity;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
