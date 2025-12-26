package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "transfer_suggestions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferSuggestion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "source_store_id", nullable = false)
    private Store sourceStore;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "target_store_id", nullable = false)
    private Store targetStore;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
    
    @Column(nullable = false)
    private Integer suggestedQuantity;
    
    @Column(nullable = false)
    private String priority = "MEDIUM";
    
    private LocalDateTime generatedAt;
    
    @Column(nullable = false)
    private String status = "PENDING";
    
    private String reason;
    
    @PrePersist
    protected void onCreate() {
        generatedAt = LocalDateTime.now();
    }
}