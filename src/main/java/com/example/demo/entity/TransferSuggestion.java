package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transfer_suggestion")
public class TransferSuggestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "source_store_id")
    private Store sourceStore;

    @ManyToOne
    @JoinColumn(name = "target_store_id")
    private Store targetStore;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "suggested_quantity")
    private int suggestedQuantity;

    @Column(name = "priority")
    private String priority;

    @Column(name = "reason")
    private String reason;

    @Column(name = "generated_at")
    private LocalDateTime generatedAt;

    // =====================
    // JPA Lifecycle
    // =====================
    @PrePersist
    public void prePersist() {
        this.generatedAt = LocalDateTime.now();
    }

    // =====================
    // Getters and Setters
    // =====================

    public Long getId() {
        return id;
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

    // ===== ORIGINAL (used by tests)
    public int getSuggestedQuantity() {
        return suggestedQuantity;
    }

    public void setSuggestedQuantity(int suggestedQuantity) {
        this.suggestedQuantity = suggestedQuantity;
    }

    // ===== ALIAS (used by service)
    public int getQuantity() {
        return suggestedQuantity;
    }

    public void setQuantity(int quantity) {
        this.suggestedQuantity = quantity;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDateTime getGeneratedAt() {
        return generatedAt;
    }

    public void setGeneratedAt(LocalDateTime generatedAt) {
        this.generatedAt = generatedAt;
    }
}
