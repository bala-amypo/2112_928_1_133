package com.example.demo.repository;

import com.example.demo.entity.InventoryLevel;
import com.example.demo.entity.Product;
import com.example.demo.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InventoryLevelRepository extends JpaRepository<InventoryLevel, Long> {

    // ✅ REQUIRED by existing services/tests
    InventoryLevel findByStoreAndProduct(Store store, Product product);
    List<InventoryLevel> findByStore_Id(Long storeId);
    List<InventoryLevel> findByProduct_Id(Long productId);

    // ✅ HQL (for transaction & HQL topic)
    @Query("FROM InventoryLevel i WHERE i.store.id = :storeId")
    List<InventoryLevel> findInventoryByStoreId(@Param("storeId") Long storeId);

    @Query("FROM InventoryLevel i WHERE i.product.id = :productId")
    List<InventoryLevel> findInventoryByProductId(@Param("productId") Long productId);
}
