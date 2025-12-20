package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // REQUIRED BY TESTS
    private String storeName;

    private String address;   // 🔥 TEST EXPECTS
    private String region;    // 🔥 TEST EXPECTS

    private boolean active = true;

    // =========================
    // FLUENT SETTERS (CRITICAL)
    // =========================

    public Store setId(Long id) {
        this.id = id;
        return this;
    }

    public Store setAddress(String address) {
        this.address = address;
        return this;
    }

    public Store setRegion(String region) {
        this.region = region;
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

    public String getAddress() {
        return address;
    }

    public String getRegion() {
        return region;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
