package com.example.demo.service;

import com.example.demo.entity.InventoryLevel;
import java.util.List;

public interface InventoryLevelService {

    // 🔥 REQUIRED BY CONTROLLER
    InventoryLevel updateInventory(Long storeId, Long productId, Integer quantity);

    List<InventoryLevel> getInventoryByStore(Long storeId);

    InventoryLevel getInventory(Long storeId, Long productId);

    // 🔥 REQUIRED BY TESTS
    InventoryLevel createOrUpdateInventory(InventoryLevel inventoryLevel);

    List<InventoryLevel> getInventoryForStore(Long storeId);

    List<InventoryLevel> getInventoryForProduct(Long productId);
}
