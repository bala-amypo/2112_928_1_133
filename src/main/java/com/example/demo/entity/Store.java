package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private String region;
    private boolean active = true;

    /* ============================
       GETTERS
       ============================ */

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // 🔥 TEST + SERVICE REQUIRED
    public String getStoreName() {
        return name;
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

    /* ============================
       FLUENT SETTERS (VERY IMPORTANT)
       ============================ */

    // 🔥 REQUIRED — fixes "void cannot be converted to Store"
    public Store setId(Long id) {
        this.id = id;
        return this;
    }

    public Store setName(String name) {
        this.name = name;
        return this;
    }

    // 🔥 ALIAS — REQUIRED BY StoreServiceImpl + TESTS
    public Store setStoreName(String name) {
        this.name = name;
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
}
