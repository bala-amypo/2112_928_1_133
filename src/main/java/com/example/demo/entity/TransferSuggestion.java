package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class TransferSuggestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;
    private String reason;
    private String priority;

    @ManyToOne
    private Store sourceStore;

    @ManyToOne
    private Store targetStore;

    @ManyToOne
    private Product product;

    // ✅ REQUIRED
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setSourceStore(Store sourceStore) {
        this.sourceStore = sourceStore;
    }

    public void setTargetStore(Store targetStore) {
        this.targetStore = targetStore;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}
