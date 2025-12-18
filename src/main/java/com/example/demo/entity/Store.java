package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Store name cannot be empty")
    private String storeName;

    @NotBlank(message = "Location cannot be empty")
    private String location;

    public Long getId() [ return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getStoreName() { return storeName; }
    public void setStoreName(String storeName) { this.storeName = storeName; }
    
    public String getLocation() { return location; }
    public void setLOCATION(String location) { this.location = location; }
   