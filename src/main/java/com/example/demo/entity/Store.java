package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // ===== GETTERS =====
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // ===== FLUENT SETTERS (🔥 REQUIRED BY TESTS) =====
    public Store setId(Long id) {
        this.id = id;
        return this;
    }

    public Store setName(String name) {
        this.name = name;
        return this;
    }
}
