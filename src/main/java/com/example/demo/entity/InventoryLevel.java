package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(
    name = "inventory_levels",
    uniqueConstraints = @UniqueConstraint(columnNames = {"store_id", "product_id"})
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InventoryLevel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private Integer quantity;

    private LocalDateTime lastUpdated;

    @PrePersist
    public void prePersist() {
        lastUpdated = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        lastUpdated = LocalDateTime.now();
    }
}
