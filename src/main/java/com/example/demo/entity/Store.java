package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String storeName;
    private String address;
    private String region;
    private boolean active = true;

    // =========================
    // FLUENT SETTERS (TEST REQ)
    // =========================

    public Store setId(Long id) {
        this.id = id;
        return this;
    }

    public Store setStoreName(String storeName) {
        this.storeName = storeName;
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

    public Store setActive(boolean active) {
        this.active = active;
        return this;
    }

    // =========================
    // GETTERS
    // =========================

    public Long getId() {
        return id;
    }

    public String getStoreName() {
        return storeName;
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
}
