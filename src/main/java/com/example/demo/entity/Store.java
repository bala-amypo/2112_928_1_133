package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔥 TESTS EXPECT THIS NAME
    private String storeName;

    // 🔥 TESTS EXPECT ACTIVE FLAG
    private boolean active = true;

    // =========================
    // FLUENT SETTERS (IMPORTANT)
    // =========================

    // ❗ MUST return Store (tests expect chaining)
    public Store setId(Long id) {
        this.id = id;
        return this;
    }

    // =========================
    // GETTERS / SETTERS
    // =========================

    public Long getId() {
        return id;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
