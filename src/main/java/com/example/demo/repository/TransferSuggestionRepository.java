package com.example.demo.repository;

import com.example.demo.entity.TransferSuggestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferSuggestionRepository
        extends JpaRepository<TransferSuggestion, Long> {

    // Used in HQL tests
    List<TransferSuggestion> findByProduct_Id(Long productId);

    // ✅ REQUIRED by InventoryBalancerServiceImpl
    List<TransferSuggestion> findBySourceStore_Id(Long storeId);
}
