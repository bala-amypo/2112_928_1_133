package com.example.demo.service;

import com.example.demo.entity.InventoryLevel;

import java.util.List;

public interface InventoryLevelService {

    InventoryLevel updateInventory(Long storeId, Long productId, Integer quantity);

    List<InventoryLevel> getInventoryByStore(Long storeId);

    List<InventoryLevel> getInventoryForProduct(Long productId);

    InventoryLevel createOrUpdateInventory(InventoryLevel inventoryLevel);

    // 🔥 REQUIRED BY CONTROLLER
    InventoryLevel getInventory(Long storeId, Long productId);
}
