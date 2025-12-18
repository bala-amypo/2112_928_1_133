package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class TransferSuggestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long fromStoreId;
    private Long toStoreId;
    private Long productId;
    private int quantity;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getFromStoreId() { return fromStoreId; }
    public void setFromStoreId(Long fromStoreId) { this.fromStoreId = fromStoreId; }

    public Long getToStoreId() { return toStoreId; }
    public void setToStoreId(Long toStoreId) { this.toStoreId = toStoreId; }

    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}
