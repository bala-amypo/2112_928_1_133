package com.example.demo.repository;

import com.example.demo.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryLevelRepository extends JpaRepository<InventoryLevel, Long> {

    Optional<InventoryLevel> findByStoreAndProduct(Store store, Product product);

    List<InventoryLevel> findByStore_Id(Long storeId);
}
