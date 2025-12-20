package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class InventoryLevel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Store store;

    @ManyToOne
    private Product product;

    private int quantity;

    // ======================
    // NORMAL GETTERS/SETTERS
    // ======================

    public Long getId() {
        return id;
    }

    public Store getStore() {
        return store;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // ======================
    // 🔥 REQUIRED BY CONTROLLER + TESTS
    // ======================

    // Controller calls inventory.setStoreId(...)
    public void setStoreId(Long storeId) {
        if (this.store == null) {
            this.store = new Store();
        }
        this.store.setId(storeId);
    }

    // Controller calls inventory.setProductId(...)
    public void setProductId(Long productId) {
        if (this.product == null) {
            this.product = new Product();
        }
        this.product.setId(productId);
    }
}
