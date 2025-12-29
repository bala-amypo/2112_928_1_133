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

    @ManyToOne
    private Store sourceStore;

    @ManyToOne
    private Store targetStore;

    @ManyToOne
    private Product product;

    private Integer suggestedQuantity;

    private String priority = "MEDIUM";

    private String status = "PENDING";

    private String reason;

    private LocalDateTime generatedAt;

    @PrePersist
    public void prePersist() {
        generatedAt = LocalDateTime.now();
    }
}
