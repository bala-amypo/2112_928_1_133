package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "stores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String storeName;

    private String address;
    private String region;

    @Column(nullable = false)
    private Boolean active = true;

    
    public boolean isActive() {
        return Boolean.TRUE.equals(active);
    }
}
