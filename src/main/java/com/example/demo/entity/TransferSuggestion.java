package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "transfer_suggestions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransferSuggestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // REQUIRED BY TESTS
    @ManyToOne
    @JoinColumn(name = "source_store_id", nullable = false)
    private Store sourceStore;

    // REQUIRED BY TESTS
    @ManyToOne
    @JoinColumn(name = "target_store_id", nullable = false)
    private Store targetStore;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    private Integer suggestedQuantity;

    private String priority = "MEDIUM";

    private String status = "PENDING";

    private String reason;

    private LocalDateTime generatedAt;

    @PrePersist
    void onCreate() {
        generatedAt = LocalDateTime.now();
    }
}
