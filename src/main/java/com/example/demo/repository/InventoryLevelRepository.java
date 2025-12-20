package com.example.demo.repository;

import com.example.demo.entity.InventoryLevel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryLevelRepository
        extends JpaRepository<InventoryLevel, Long> {

    List<InventoryLevel> findByStore_Id(Long storeId);

    List<InventoryLevel> findByProduct_Id(Long productId);

    // 🔥 SERVICE ALIASES
    default List<InventoryLevel> findByStoreId(Long storeId) {
        return findByStore_Id(storeId);
    }

    default List<InventoryLevel> findByProductId(Long productId) {
        return findByProduct_Id(productId);
    }
}
