package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Product {

    @Id
    private Long id;

    private String name;
    private String category;
    private Boolean active;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}
