package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String region;
    private String address;
    private boolean active = true;

    // =========================
    // 🔥 FLUENT SETTERS (TEST REQUIRED)
    // =========================

    public Store setId(Long id) {
        this.id = id;
        return this;
    }

    public Store setName(String name) {
        this.name = name;
        return this;
    }

    public Store setRegion(String region) {
        this.region = region;
        return this;
    }

    public Store setAddress(String address) {
        this.address = address;
        return this;
    }

    public Store setActive(boolean active) {
        this.active = active;
        return this;
    }

    // =========================
    // 🔥 GETTERS (TEST REQUIRED)
    // =========================

    public Long getId() {
        return id;
    }

    public String getStoreName() {
        return name;
    }

    public String getRegion() {
        return region;
    }

    public String getAddress() {
        return address;
    }

    public boolean isActive() {
        return active;
    }
}
