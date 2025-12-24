package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "stores")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String storeName;
    private String address;
    private String region;

    private Boolean active = true;

    public Store() {
    }

    // ---------- GETTERS ----------

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

    // ✔ Required by some services
    public Boolean getActive() {
        return active;
    }

    // ✔ Required by TEST CASES
    public Boolean isActive() {
        return active;
    }

    // ---------- SETTERS ----------

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

    public void setActive(Boolean active) {
        this.active = active;
    }
}
