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

    public Store() {}

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

    // ✅ REQUIRED BY SERVICES
    public boolean getActive() {
        return active;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
