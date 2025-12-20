package com.example.demo.service;

import com.example.demo.entity.InventoryLevel;

import java.util.List;

public interface InventoryLevelService {

    // 🔥 REQUIRED BY CONTROLLER
    InventoryLevel updateInventory(Long storeId, Long productId, Integer quantity);

    // 🔥 REQUIRED BY CONTROLLER
    List<InventoryLevel> getInventoryByStore(Long storeId);

    // 🔥 REQUIRED BY TESTS
    List<InventoryLevel> getInventoryForProduct(Long productId);

    // 🔥 REQUIRED BY TESTS
    InventoryLevel createOrUpdateInventory(InventoryLevel inventoryLevel);
}
