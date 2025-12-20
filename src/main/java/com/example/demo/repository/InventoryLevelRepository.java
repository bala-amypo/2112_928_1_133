package com.example.demo.repository;

import com.example.demo.entity.InventoryLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryLevelRepository extends JpaRepository<InventoryLevel, Long> {

    // REQUIRED BY SERVICE + TESTS
    List<InventoryLevel> findByStoreId(Long storeId);

    List<InventoryLevel> findByProductId(Long productId);
}
